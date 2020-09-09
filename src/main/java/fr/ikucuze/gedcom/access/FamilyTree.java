package fr.ikucuze.gedcom.access;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ikucuze.gedcom.structure.subrecord.FamGroupRecord;
import fr.ikucuze.gedcom.structure.subrecord.IndividualRecord;
import fr.ikucuze.gedcom.structure.toplevel.LineageLinkedGedcomFile;
import fr.ikucuze.gedcom.structure.toplevel.LineageLinkedRecord;

public class FamilyTree {
	private final LineageLinkedGedcomFile gedcom;

	private static final Comparator<String> xrefComparator = new Comparator<String>() {
		Pattern p = Pattern.compile("@.*?(\\d+)@");
		@Override
		public int compare(String o1, String o2) {
			String i1=null, i2=null;
			Matcher m = p.matcher(o1);
			if (m.matches()) {
				i1 = m.group(1);
			}
			m.reset(o2);
			if (m.matches()) {
				i2 = m.group(1);
			}
			return i1.compareTo(i2);
		}
	};
	

	public Map<String, Person> mapPersons = new TreeMap<>(xrefComparator);
	public Map<String, Relation> mapRelations = new TreeMap<>(xrefComparator);
	
	
	public FamilyTree(LineageLinkedGedcomFile gedcom) {
		this.gedcom = gedcom;
		analyse();
	}

	private void analyse() {
		List<LineageLinkedRecord> records = gedcom.records.lineageLinkedRecords;
		for (LineageLinkedRecord record : records) {
			analyse(record.individual);
		}
		for (LineageLinkedRecord record : records) {
			analyse(record.family);
		}
	}

	private void analyse(IndividualRecord individual) {
		if (individual==null) {
			return;
		}
		Person person = new Person(this, individual);
		mapPersons.put(person.getXref(), person);
	}

	private void analyse(FamGroupRecord family) {
		if (family==null) {
			return;
		}
		Relation rel = new Relation(this, family);
		mapRelations.put(rel.getXref(), rel);
	}

	
}
