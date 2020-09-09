package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;
import fr.ikucuze.gedcom.structure.primitive.ReferenceNumberStructure;

@GedcomInlinedRecord(name="OBJE")
public class MultimediaRecord extends GedcomObject {
	public static class File extends GedcomObject {
		public static class Form extends GedcomObject {
			@GedcomField(name="TYPE")
			public String type;
		}

		@GedcomField(name="FORM")
		public Form form;
		@GedcomField(name="TITL")
		public String title;
	}

	@GedcomField(name="FILE")
	public File file;
	@GedcomField(name="REFN")
	public ReferenceNumberStructure referenceNumber;
	@GedcomField
	public List<NoteStructure> note;
	@GedcomField
	public List<SourceCitation> source;
	@GedcomField
	public ChangeDate changeDate;	
}
