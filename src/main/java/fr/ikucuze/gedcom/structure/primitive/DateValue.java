package fr.ikucuze.gedcom.structure.primitive;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ikucuze.gedcom.structure.GedcomObject;

public class DateValue extends GedcomObject {
	public static class Date implements Comparable<Date> {
		
		public static final Comparator<Date> comparator = new Comparator<DateValue.Date>() {
			public int compare(Date o1, Date o2) {
				return 0;
			};
		};
		
		private Map<String, String> tokens;

		private Date(Map<String, String> tokens) {
			this.tokens = tokens;
		}
		
		private ESCAPE escape = ESCAPE.GREGORIAN;
		
		@Override
		public String toString() {
			return tokens.toString();
		}
		
		private enum DATE_PARTS {
			YEAR("<\\d{3,4}>"),
			DUAL_STYLE_YEAR("<YEAR>/<\\d{2,4}>"),
			MONTH("[JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC]"),
			MONTH_HEBR("[TSH|CSH|KSL|TVT|SHV|ADR|ADS|NSN|IYR|SVN|TMZ|AAV|ELL]"),
			MONTH_FREN("[VEND|BRUM|FRIM|NIVO|PLUV|VENT|GERM|FLOR|PRAI|MESS|THER|FRUC|COMP]"),
			DAY("<\\d{1,2}>"),
			BEFORE_COMMON_ERA("[BCE|BC|B.C.]");
			
			private String pattern;

			DATE_PARTS(String pattern) {
				this.pattern = pattern;
			}

			public String parse(String texte, AtomicInteger b) {
				String result = "";
				int b_orig = b.get();
				int a=0;
				while (a<pattern.length() && b.get()<texte.length()) {
					switch (pattern.charAt(a)) {
						case '<':
						{
							int a2 = pattern.indexOf('>', ++a);
							String item = pattern.substring(a,a2);
							a = ++a2;
							try {
								DATE_PARTS datePart = DATE_PARTS.valueOf(item);
								String partial = datePart.parse(texte, b);
								if (partial==null) {
									//fail
									b.set(b_orig);
									return null;
								}
								result += partial;
							} catch (IllegalArgumentException ex) {
								Pattern pat = Pattern.compile(item);
								Matcher matcher = pat.matcher(texte);
								matcher.region(b.get(), texte.length());
								boolean find = matcher.find();
								if (find && matcher.start()==b.get()) { // hum
									String match = matcher.group(0);
									b.addAndGet( match.length() );
									result += match;
								} else {
									//fail
									b.set(b_orig);
									return null;
								}
							}
							break;
						}
						case '[':
						{
							int a2 = pattern.indexOf(']', ++a);
							String item = pattern.substring(a,a2);
							a = ++a2;
							String[] split = item.split("\\|");
							if (split.length==1) {
								String s = split[0];
								if (texte.substring(b.get()).startsWith(s)) {
									b.addAndGet(s.length());
									result += s;
								}
							} else {
								boolean matched = false;
								for (String s : split) {
									int end = b.get()+s.length();
									if ( texte.length()>=end &&
											texte.substring(b.get(),end).equals(s)) {
										b.addAndGet(s.length());
										result += s;
										matched = true;
										break;
									}
								}
								if (!matched) {
									//fail
									b.set(b_orig);
									return null;
								}
							}
							break;
						}
						default:
							result += pattern.charAt(a);
							if (pattern.charAt(a++) != texte.charAt(b.getAndIncrement())) {
								//fail
								b.set(b_orig);
								return null;
							}
							break;
					}
				}
				if (a!=pattern.length()) {
					//fail
					b.set(b_orig);
					return null;
				}
				return result;
			}
		}
		private enum ESCAPE {
			HEBREW("DHEBREW",
					"<DAY> <MONTH_HEBR> <YEAR>",
					"<MONTH_HEBR> <YEAR>",
					"<YEAR>"
					),
			FRANCH_REVOL("DFRENCH R",
					"<DAY> <MONTH_FREN> <YEAR>",
					"<MONTH_FREN> <YEAR>",
					"<YEAR>"
					),
			GREGORIAN("DGREGORIAN",
					"<DUAL_STYLE_YEAR>", 	/*False, but seen "1615/1616" */
					"<MONTH> <DUAL_STYLE_YEAR>",
					"<DAY> <MONTH> <DUAL_STYLE_YEAR>",
					"<DAY> <MONTH> <YEAR>",
					"<MONTH> <YEAR>",
					"<DAY> <MONTH>",
					"<YEAR>[ <BEFORE_COMMON_ERA>]"
					),
			JULIAN("DJULIAN",
					"<DAY> <MONTH> <DUAL_STYLE_YEAR>",
					"<DAY> <MONTH> <YEAR>",
					"<MONTH> <DUAL_STYLE_YEAR>",
					"<MONTH> <YEAR>",
					"<YEAR>[ <BEFORE_COMMON_ERA>]"
					),
			UNKNOWN("DUNKNOWN", ".*");
			
			private String code;
			private String[] patterns;

			private ESCAPE(String code, String... patterns) {
				this.code = code;
				this.patterns = patterns;
			}

			public static ESCAPE forCode(String escape) {
				for (ESCAPE value : values()) {
					if (value.code.equals(escape)) {
						return value;
					}
				}
				return null;
			}

			public Date parseDate(String texte, AtomicInteger b) {
				for (String pattern : patterns) {
					Map<String,String> tokens = parseDate(texte, pattern, b);
					if (tokens!=null) {
						Date date = new Date(tokens);
						date.escape = this;
						return date;
					}
				}
				return null;
			}
			
			private static Map<String,String> parseDate(String texte, String pattern, AtomicInteger b) {
				int b_orig = b.get();
				int a=0;
				Map<String,String> tokens = new HashMap<>();
				while (a<pattern.length()) {
					switch (pattern.charAt(a)) {
						case '<':
						{
							int a2 = pattern.indexOf('>', ++a);
							String item = pattern.substring(a,a2);
							a = ++a2;
							DATE_PARTS datePart = DATE_PARTS.valueOf(item);
							String value = datePart.parse(texte, b);
							if (value==null) {
								//fail
								b.set(b_orig);
								return null;
							}
							tokens.put(item, value);
							break;
						}
						case '[':
						{
							int a2 = pattern.indexOf(']', ++a);
							String item = pattern.substring(a,a2);
							a = ++a2;
							Map<String, String> subTokens = parseDate(texte, item, b);
							if (subTokens==null) {
								//fail (optionnal)
							} else {
								tokens.putAll(subTokens);
							}
							break;
						}
						default:
							if (b.get()==texte.length() ||
								pattern.charAt(a) != texte.charAt(b.getAndIncrement()))
							{
								//fail
								b.set(b_orig);
								return null;
							}
							// was a greeding space?
							if (pattern.charAt(a)==' ') {
								while (texte.charAt(b.get())==' ') {
									b.incrementAndGet();
								}
							}
							a++;
							break;
					}
				}
				if (a!=pattern.length()) {
					//fail
					b.set(b_orig);
					return null;
				}
				return tokens;
			}
		}
	
		private static ESCAPE parseEscape(String texte, AtomicInteger b) {
			int indexA = texte.indexOf("@#",b.get());
			if (indexA!=-1) {
				int indexB = texte.indexOf("@",b.get()+1);
				if (indexB!=-1) {
					b.set(indexB+1);
					if (texte.charAt(b.get())==' ') {
						b.incrementAndGet();
					}
					String escape = texte.substring(indexA, indexB);
					ESCAPE esc = ESCAPE.forCode(escape);
					if (esc!=null) {
						return esc;
					}
				}
			}
			return ESCAPE.GREGORIAN;
		}

		public static Date parseDate(String texte, AtomicInteger b) {
			ESCAPE escape = parseEscape(texte, b);
			return escape.parseDate(texte,b);
		}

		@Override
		public int compareTo(Date o) {
			if (o==null) {
				return 1;
			}
//			if (tokens.get(DATE_PARTS.YEAR.name()).compareTo(o.tokens.get(DATE_PARTS.YEAR.name())))
			return 0;
		}
	}
	
	public enum DATE_FORMAT {
		DATE("<DATE>", 1, false),
		DATE_PERIOD_FROM("FROM <DATE>", 1, false),
		DATE_PERIOD_TO("TO <DATE>", 1, false),
		DATE_PERIOD_FROM_TO("FROM <DATE> TO <DATE>", 2, false),
		DATE_RANGE_BEFORE("BEF <DATE>", 1, false),
		DATE_RANGE_AFTER("AFT <DATE>", 1, false),
		DATE_RANGE_BETWEEN("BET <DATE> AND <DATE>", 2, false),
		DATE_APPROXIMATED_ABOUT("ABT <DATE>", 1, false),
		DATE_APPROXIMATED_CALCULATED("CAL <DATE>", 1, false),
		DATE_APPROXIMATED_ESTIMATED("EST <DATE>", 1, false),
		DATE_PHRASE("(<TEXT>)", 0, true),
		DATE_INTERPRETED("INT <DATE> (<TEXT>)", 1, true);
//		DATE_INVALID("<TEXT>", 0, true);
		
		
		private String pattern;
		private int dateCount;
		private boolean withText;

		private DATE_FORMAT(String pattern, int dateCount, boolean withText) {
			this.pattern = pattern;
			this.dateCount = dateCount;
			this.withText = withText;
		}

		public boolean parse(DateValue dateValue) {
			String texte = dateValue.value.toUpperCase().trim();
			int a=0;
			AtomicInteger b=new AtomicInteger();
			Date date1=null;
			Date date2=null;
			String text=null;
			while (a<pattern.length() && b.get()<texte.length()) {
				switch (pattern.charAt(a)) {
					case '<':
						int a2 = pattern.indexOf('>', ++a);
						String item = pattern.substring(a,a2);
						a = ++a2;
						switch (item) {
						case "DATE":
							Date date = Date.parseDate(texte, b);
							if (date == null) {
								return false;
							}
							if (date1==null) {
								date1 = date;
							} else {
								date2 = date;
							}
							break;
						case "TEXT":
							int b2;
							if (pattern.endsWith(")")) {
								b2 = texte.lastIndexOf(')');
							} else {
								b2 = texte.length();
							}
							text = texte.substring(b.get(), b2);
							b.addAndGet(text.length());
							break;
						}
						break;
					default:
						if (pattern.charAt(a) != texte.charAt(b.getAndIncrement())) {
							return false;
						}
						// was a greeding space?
						if (pattern.charAt(a)==' ') {
							while (texte.charAt(b.get())==' ') {
								b.incrementAndGet();
							}
						}
						a++;
						break;
				}
			}
			if (a!=pattern.length() || b.get()!=texte.length()) {
				return false;
			}
			dateValue.date1 = date1;
			dateValue.date2 = date2;
			dateValue.dateText = text;
			return true;
		}

		public String toString(DateValue dateValue) {
			StringBuilder sb = new StringBuilder();
			sb.append(this.name());
			sb.append(" : ");
			if (dateCount>0) {
				sb.append(dateValue.date1);
			}
			if (dateCount>1) {
				sb.append(" - ");
				sb.append(dateValue.date2);
			}
			if (withText) {
				if (!dateValue.invalid) {
					sb.append(" (");
				}
				sb.append(dateValue.dateText);
				if (!dateValue.invalid) {
					sb.append(")");
				}
			}
			return sb.toString();
		}

	}
	
	private boolean parsed = false;
	public DateValue() {
	}
	public DateValue(String string) {
		this.setValue(string);
	}
	
	@Override
	public void setValue(String value) {
		super.setValue(value);
		parsed=false;
		parse();
	}

	private void parse() {
		if (parsed) {
			return;
		}
		parsed = true;
		for (DATE_FORMAT type : DATE_FORMAT.values()) {
			if (type.parse(this)) {
				this.dateFormat = type;
				return;
			}
		}
		throw new RuntimeException("unparsable date : "+value);
	}
	
	private DATE_FORMAT dateFormat;
	public DATE_FORMAT getDateType() {
		return dateFormat;
	}
	private boolean invalid;
	private Date date1;
	public Date getDate1() {
		return date1;
	}
	private Date date2;
	public Date getDate2() {
		return date2;
	}
	private String dateText;
	public String getDateText() {
		return dateText;
	}

	@Override
	public String toString() {
		parse();
		return dateFormat.toString(this);
	}

	public static void main(String[] args) {
		DateValue dateValue;
//		dateValue = new DateValue("25 DEC 2020");
//		System.out.println(dateValue.toString());
//		dateValue = new DateValue("1978");
//		System.out.println(dateValue.toString());
//		dateValue = new DateValue("BET 23 MAR 1769 AND 24 MAR 1769");
//		System.out.println(dateValue.toString());
//		dateValue = new DateValue("       1815/1816");
//		System.out.println(dateValue.toString());
		dateValue = new DateValue(" 2 APR  742");
		System.out.println(dateValue.toString());
	}
}
