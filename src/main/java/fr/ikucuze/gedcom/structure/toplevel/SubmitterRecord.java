package fr.ikucuze.gedcom.structure.toplevel;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;
import fr.ikucuze.gedcom.structure.subrecord.AddressStructure;
import fr.ikucuze.gedcom.structure.subrecord.ChangeDate;
import fr.ikucuze.gedcom.structure.subrecord.CommentsWithCont;
import fr.ikucuze.gedcom.structure.subrecord.MultimediaLink;
import fr.ikucuze.gedcom.structure.subrecord.NoteStructure;

@GedcomInlinedRecord(name="SUBM")
public class SubmitterRecord extends GedcomObject {
	@GedcomField(name="NAME")
	public String name;
	@GedcomField
	public AddressStructure address;
	@GedcomField
	public List<MultimediaLink> multimedia;
	@GedcomField(name="RIN")
	public String recordId;
	@GedcomField
	public List<NoteStructure> note;
	@GedcomField
	public ChangeDate changeDate;
	@GedcomField(name="COMM")
	public CommentsWithCont comments;
}
