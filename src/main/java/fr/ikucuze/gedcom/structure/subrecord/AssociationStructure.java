package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="ASSO")
public class AssociationStructure extends GedcomObject {
	@GedcomField(name="RELA")
	public String relation;
	@GedcomField
	public List<SourceCitation> source;
	@GedcomField
	public List<NoteStructure> note;
}
