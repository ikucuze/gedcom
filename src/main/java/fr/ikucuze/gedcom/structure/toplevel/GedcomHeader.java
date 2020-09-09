package fr.ikucuze.gedcom.structure.toplevel;

import java.util.HashMap;
import java.util.Map;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomEnum;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;
import fr.ikucuze.gedcom.structure.annotation.GedcomPrimitive;
import fr.ikucuze.gedcom.structure.primitive.GedcomVersionNumber;

@GedcomInlinedRecord(name="HEAD")
public abstract class GedcomHeader extends GedcomObject {
	public static class GEDC extends GedcomObject {
		@GedcomPrimitive(regexp="LINEAGE-LINKED")	// FIXME : decide where to define it
		public static class Form extends GedcomObject {
			@GedcomField(name="VERS")
			public GedcomVersionNumber version;
			public String getForm() {
				return this.value;
			}
		}
		@GedcomField(name="VERS")
		public GedcomVersionNumber version;
		@GedcomField(name="FORM", mandatory=true, regexp="LINEAGE-LINKED")
		public Form form;
	}
	
	public static enum Charset implements GedcomEnum {
		UTF_8("UTF-8"),
		UTF_16("UNICODE"),
		ANSEL("ANSEL"),
		ASCII("ASCII");
		private static Map<String,Charset> reverse=new HashMap<>();
		private String gedcomValue;
		Charset(String gedComValue) {
			this.gedcomValue = gedComValue;
		}
		public String getGedcomValue() {
			return this.gedcomValue;
		}
		public static Charset getEnumForValue(String s) {
			if (reverse.isEmpty()) {
				synchronized(reverse) {
					if (reverse.isEmpty()) {
						for (Charset c : values()) {
							reverse.put(c.gedcomValue, c);
						}
					}
				}
			}
			return reverse.get(s);
		}
	}
	
	@GedcomField(name="GEDC")
	public GEDC gedcom;
	@GedcomField(name="CHAR")
	public Charset charset;	// UTF-8 | UNICODE (=UTF-16) | @dep: ANSEL | @dep: ASCII 
}
