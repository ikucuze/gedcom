package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="NAME", mandatoryValue=true)
public class PersonalNameStruct extends GedcomObject {
	public static class NamePhonetic extends GedcomObject {
		@GedcomField(name="TYPE", mandatory=true)
		public String phonetisationMethod;
		@GedcomField
		public PersonalNamePieces pieces;
	}
	public static class NameRomanised extends GedcomObject {
		@GedcomField(name="TYPE", mandatory=true)
		public String romanisationMethod;
		@GedcomField
		public PersonalNamePieces pieces;
	}

	@GedcomField(name="TYPE")
	public String nameType;
	@GedcomField
	public PersonalNamePieces pieces;
	@GedcomField(name="FONE")
	public List<NamePhonetic> namePhonetic;
	@GedcomField(name="ROMN")
	public List<NameRomanised> nameRomanised;
}
