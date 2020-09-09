package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;
import fr.ikucuze.gedcom.structure.primitive.ReferenceNumberStructure;

@GedcomInlinedRecord(name="FAM")
public class FamGroupRecord extends GedcomObject {
	@GedcomField
	public List<FamilyEventStructure> events;
	@GedcomField(name="HUSB")
	public String husband;
	@GedcomField(name="WIFE")
	public String wife;
	@GedcomField(name="CHIL")
	public List<String> children;
	@GedcomField(name="NCHI")
	public String childrenCount;
	@GedcomField(name="REFN")
	public ReferenceNumberStructure referenceNumber;
	@GedcomField(name="RIN")
	public String recordId;
	@GedcomField
	public ChangeDate changeDate;
	@GedcomField
	public List<NoteStructure> notes;
	@GedcomField
	public List<SourceCitation> sources;
	@GedcomField
	public List<MultimediaLink> multimedia;
}
