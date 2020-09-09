package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;
import fr.ikucuze.gedcom.structure.primitive.ReferenceNumberStructure;

@GedcomInlinedRecord(name="INDI")
public class IndividualRecord extends GedcomObject {
	@Deprecated
	@GedcomField(name="RESN")
	public String restrictionNotice;
	@GedcomField
	public List<PersonalNameStruct> name;
	@GedcomField(name="SEX")
	public String sex;
	@GedcomField
	public List<IndividualEventStructure> events;
	@GedcomField
	public List<IndividualAttributeStructure> attributes;
	@GedcomField
	public List<ChildToFamilyLink> childToFamily;
	@GedcomField
	public List<SpouseToFamilyLink> spouseToFamily;
	@GedcomField
	public List<AssociationStructure> associations;
	@GedcomField(name="REFN")
	public List<ReferenceNumberStructure> referenceNumber;
	@GedcomField(name="RIN")
	public String recordId;
	@GedcomField
	public ChangeDate changeDate;
	@GedcomField
	public List<NoteStructure> note;
	@GedcomField
	public List<SourceCitation> source;
	@GedcomField
	public List<MultimediaLink> multimedia;	
}
