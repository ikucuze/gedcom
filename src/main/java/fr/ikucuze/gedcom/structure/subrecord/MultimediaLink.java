package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="OBJE")
public class MultimediaLink extends GedcomObject {
	// just a value in 5.5.5
	
	@Deprecated
	@GedcomField(name="FORM")
	public String format;
	@Deprecated
	@GedcomField(name="TITL")
	public String title;
	@Deprecated
	@GedcomField(name="FILE")
	public String file;
	@Deprecated
	@GedcomField
	public List<NoteStructure> notes;
}
