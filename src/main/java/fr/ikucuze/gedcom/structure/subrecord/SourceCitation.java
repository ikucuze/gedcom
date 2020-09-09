package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="SOUR")
public class SourceCitation extends GedcomObject {
	public static class EventType extends GedcomObject {
		@GedcomField(name="ROLE")
		public String roleInEvent;
	}
	public static class DataType extends GedcomObject {
		@GedcomField(name="DATE")
		public String date;
		@GedcomField(name="TEXT")
		public List<String> text;
	}
	
	@GedcomField(name="PAGE")
	public String page;
	@GedcomField(name="EVEN")
	public EventType citedFrom;
	@GedcomField(name="DATA")
	public DataType data;
	@GedcomField
	public List<MultimediaLink> multimediaLinks;
	@GedcomField
	public List<NoteStructure> notes;
	@GedcomField(name="QUAY")
	public String qualiyOfData;
}
