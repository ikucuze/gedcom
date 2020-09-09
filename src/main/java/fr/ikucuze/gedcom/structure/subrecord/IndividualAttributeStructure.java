package fr.ikucuze.gedcom.structure.subrecord;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class IndividualAttributeStructure extends GedcomObject {
	@GedcomField(name="CAST")
	public IndividualEventDetail casteName;
	@GedcomField(name="DSCR")
	public IndividualEventDetail physicalDescription;
	@GedcomField(name="EDUC")
	public IndividualEventDetail scholasticAchievement;
	@GedcomField(name="IDNO")
	public IndividualEventDetail idNumber;
	@GedcomField(name="NATI")
	public IndividualEventDetail nationalOrTribalOrigin;
	@GedcomField(name="NCHI")
	public IndividualEventDetail countOfChildren;
	@GedcomField(name="NMR")
	public IndividualEventDetail numberOfRelatonships;
	@GedcomField(name="OCCU")
	public IndividualEventDetail occupation;
	@GedcomField(name="PROP")
	public IndividualEventDetail possessions;
	@GedcomField(name="RELI")
	public IndividualEventDetail religiousAffiliation;
	@GedcomField(name="RESI")
	public IndividualEventDetail residence;
	@GedcomField(name="TITL")
	public IndividualEventDetail nobilityTypeTitle;
	@GedcomField(name="FACT")
	public IndividualEventDetail fact;

	// should be in every sub-types but...
	@GedcomField(name="TYPE")
	public String type;
}
