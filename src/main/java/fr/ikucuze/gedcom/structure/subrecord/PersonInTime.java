package fr.ikucuze.gedcom.structure.subrecord;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public final class PersonInTime extends GedcomObject {
	@GedcomField(name="AGE")
	public String ageAtEvent;
}
