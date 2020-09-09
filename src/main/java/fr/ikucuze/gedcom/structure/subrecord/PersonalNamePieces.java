package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class PersonalNamePieces extends GedcomObject {
	@GedcomField(name="NPFX")
	public String namePrefix;
	@GedcomField(name="GIVN")
	public String givenName;
	@GedcomField(name="NICK")
	public String nickname;
	@GedcomField(name="SPFX")
	public String surnamePrefix;
	@GedcomField(name="SURN")
	public String surname;
	@GedcomField(name="NSFX")
	public String nameSuffix;
	@GedcomField
	public List<NoteStructure> notes;
	@GedcomField
	public List<SourceCitation> sources;
}
