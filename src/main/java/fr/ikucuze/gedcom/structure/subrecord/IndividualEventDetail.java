package fr.ikucuze.gedcom.structure.subrecord;

import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class IndividualEventDetail extends EventDetail {
	@GedcomField(name="AGE")
	public String ageAtEvent;
}
