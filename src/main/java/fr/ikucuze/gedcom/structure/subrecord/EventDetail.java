package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.primitive.DateValue;

public class EventDetail extends GedcomObject {
	@GedcomField(name="TYPE")
	public String type;
	@GedcomField(name="DATE")
	public DateValue date;
	@GedcomField
	public PlaceStructure place;
	@GedcomField
	public AddressStructure address;
	@GedcomField(name="AGNC")
	public String responsibleAgency;
	@GedcomField(name="RELI")
	public String religiousAffiliation;
	@GedcomField(name="CAUS")
	public String cause;
	@GedcomField
	public List<NoteStructure> notes;
	@GedcomField
	public List<SourceCitation> sources;
	@GedcomField
	public List<MultimediaLink> multimedias;
}
