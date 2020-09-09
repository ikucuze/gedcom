package fr.ikucuze.gedcom.structure.toplevel;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.subrecord.FamGroupRecord;
import fr.ikucuze.gedcom.structure.subrecord.IndividualRecord;
import fr.ikucuze.gedcom.structure.subrecord.MultimediaRecord;
import fr.ikucuze.gedcom.structure.subrecord.NoteRecord;
import fr.ikucuze.gedcom.structure.subrecord.RepositoryRecord;
import fr.ikucuze.gedcom.structure.subrecord.SourceRecord;

public class LineageLinkedRecord extends GedcomObject {
	@GedcomField
	public IndividualRecord individual;
	@GedcomField
	public FamGroupRecord family;
	@GedcomField
	public MultimediaRecord media;
	@GedcomField
	public NoteRecord notes;
	@GedcomField
	public RepositoryRecord repository;
	@GedcomField
	public SourceRecord source;
}
