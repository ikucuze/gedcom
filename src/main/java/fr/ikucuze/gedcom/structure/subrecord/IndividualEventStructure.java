package fr.ikucuze.gedcom.structure.subrecord;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class IndividualEventStructure extends GedcomObject {
	public static class IndividualEventDetailWithFAMC extends IndividualEventDetail {
		@GedcomField(name="FAMC")
		public String childToFamily;
	}
	public static class IndividualEventDetailWithFAMCWithADOP extends IndividualEventDetail {
		public static class FAMCWithADOP extends GedcomObject {
			@GedcomField(name="ADOP")
			public String adoptedBy;
		}
		@GedcomField(name="FAMC")
		public FAMCWithADOP childToFamily;
	}

	@GedcomField(name="BIRT")
	public IndividualEventDetailWithFAMC birth;	
	@GedcomField(name="CHR")
	public IndividualEventDetailWithFAMC christening;	
	@GedcomField(name="DEAT")
	public IndividualEventDetail death;
	@GedcomField(name="BURI")
	public IndividualEventDetail burial;
	@GedcomField(name="CREM")
	public IndividualEventDetail cremation;
	@GedcomField(name="ADOP")
	public IndividualEventDetailWithFAMCWithADOP adoption;
	@GedcomField(name="BAPM")
	public IndividualEventDetail baptism;
	@GedcomField(name="BARM")
	public IndividualEventDetail barMitzvah;
	@GedcomField(name="BASM")
	public IndividualEventDetail basMitzvah;
	@GedcomField(name="CHRA")
	public IndividualEventDetail adultChristening;
	@GedcomField(name="CONF")
	public IndividualEventDetail confirmation;
	@GedcomField(name="FCOM")
	public IndividualEventDetail firstCommunion;
	@GedcomField(name="NATU")
	public IndividualEventDetail naturalisation;
	@GedcomField(name="EMIG")
	public IndividualEventDetail emigration;
	@GedcomField(name="IMMI")
	public IndividualEventDetail immigration;
	@GedcomField(name="CENS")
	public IndividualEventDetail census;
	@GedcomField(name="PROB")
	public IndividualEventDetail probate;
	@GedcomField(name="WILL")
	public IndividualEventDetail will;
	@GedcomField(name="GRAD")
	public IndividualEventDetail graduation;
	@GedcomField(name="RETI")
	public IndividualEventDetail retirement;
	@GedcomField(name="EVEN")
	public IndividualEventDetail event;
}
