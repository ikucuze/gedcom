package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="FAMC")
public class ChildToFamilyLink extends GedcomObject {
	@GedcomField(name="PEDI")
	public String pedigree;
	@GedcomField
	public List<NoteStructure> note;
}
