package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="PLAC")
public class PlaceStructure extends GedcomObject {
	public static class PlacePhonetic extends GedcomObject {
		@GedcomField(name="TYPE")
		public String phonetisationMethod;
	}
	public static class PlaceRomanised extends GedcomObject {
		@GedcomField(name="TYPE")
		public String romanisationMethod;
	}
	public static class Map extends GedcomObject {
		@GedcomField(name="LATI")
		public String placeLatitude;
		@GedcomField(name="LONG")
		public String placeLongitude;
	}
	
	@GedcomField(name="FONE")
	public List<PlacePhonetic> placePhonetic;
	@GedcomField(name="ROMN")
	public List<PlaceRomanised> placeRomanised;
	@GedcomField(name="MAP")
	public Map map;
	@GedcomField
	public List<NoteStructure> notes;
}
