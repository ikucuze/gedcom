package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;
import fr.ikucuze.gedcom.structure.primitive.ReferenceNumberStructure;

@GedcomInlinedRecord(name="SOUR")
public class SourceRecord extends GedcomObject {
	public static class Data extends GedcomObject {
		public static class Event extends GedcomObject {
			@GedcomField(name="DATE")
			public String date;
			@GedcomField(name="PLAC")
			public String place;
		}
		
		@GedcomField(name="EVEN")
		public Event event;
		@GedcomField(name="AGNC")
		public String agency;
		@GedcomField
		public NoteStructure note;
	}
	
	@GedcomField(name="DATA")
	public Data data;
	@GedcomField(name="AUTH")
	public String author;
	@GedcomField(name="TITL")
	public String title;
	@GedcomField(name="ABBR")
	public String filed_by;
	@GedcomField(name="PUBL")
	public String publicationFact;
	@GedcomField(name="TEXT")
	public String text;
	@GedcomField
	public List<SourceRepositoryCitation> repositoryCitation;
	@GedcomField(name="REFN")
	public List<ReferenceNumberStructure> referenceNumber;
	@GedcomField(name="RIN")
	public String recordId;
	@GedcomField
	public ChangeDate changeDate;
	@GedcomField
	public List<NoteStructure> note;
	@GedcomField
	public List<MultimediaLink> multimedia;	

}
