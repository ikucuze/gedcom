package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="CHAN", mandatoryValue=true)
public class ChangeDate extends GedcomObject {
	public static class Date extends GedcomObject {
		@GedcomField(name="TIME")
		public String time;
	}
	
	@GedcomField(name="DATE", mandatory=true)
	public Date date;
	@GedcomField
	public List<NoteStructure> notes;
}
