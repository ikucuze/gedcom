package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class AddressStructure extends GedcomObject {
	public static class Address extends GedcomObject {
		@Deprecated
		@GedcomField(name="CONT")
		public List<String> addressCont;
		@GedcomField(name="ADR1")
		public String line1;
		@GedcomField(name="ADR2")
		public String line2;
		@GedcomField(name="ADR3")
		public String line3;
		@GedcomField(name="CITY")
		public String city;
		@GedcomField(name="STAE")
		public String state;
		@GedcomField(name="POST")
		public String postalCode;
		@GedcomField(name="CTRY")
		public String country;
	}
	
	@GedcomField(name="ADDR")
	public Address address;
	@GedcomField(name="PHON")
	public List<String> phoneNumber;
	@GedcomField(name="EMAIL")
	public List<String> email;
	@GedcomField(name="FAX")
	public List<String> fax;
	@GedcomField(name="WWW")
	public List<String> webPage;
}
