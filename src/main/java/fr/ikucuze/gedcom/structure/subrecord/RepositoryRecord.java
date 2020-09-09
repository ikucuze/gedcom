package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;
import fr.ikucuze.gedcom.structure.primitive.ReferenceNumberStructure;

@GedcomInlinedRecord(name="REPO")
public class RepositoryRecord extends GedcomObject {
	@GedcomField(name="NAME")
	public String name;
	@GedcomField
	public AddressStructure address;
	@GedcomField
	public List<NoteStructure> notes;
	@GedcomField(name="REFN")
	public List<ReferenceNumberStructure> referenceNumber;
	@GedcomField(name="RIN")
	public String recordId;
	@GedcomField
	public ChangeDate changeDate;
}
