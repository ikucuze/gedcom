package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;
import fr.ikucuze.gedcom.structure.primitive.ReferenceNumberStructure;

@GedcomInlinedRecord(name="NOTE")
public class NoteRecord extends GedcomObject {
	@GedcomField(name="REFN")
	public List<ReferenceNumberStructure> referenceNumber;
	@GedcomField(name="RIN")
	public String recordId;
	@GedcomField
	public List<SourceCitation> sources;
	@GedcomField
	public ChangeDate changeDate;
}
