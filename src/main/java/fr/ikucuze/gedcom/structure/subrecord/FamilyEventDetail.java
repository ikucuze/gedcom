package fr.ikucuze.gedcom.structure.subrecord;

import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class FamilyEventDetail extends EventDetail {
	@GedcomField(name="HUSB")
	public PersonInTime husband;
	@GedcomField(name="WIFE")
	public PersonInTime wife;
}
