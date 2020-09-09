package fr.ikucuze.gedcom.structure.toplevel;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class GedcomFile<H extends GedcomHeader, F extends GedcomFormRecords> extends GedcomObject {
	@GedcomField(mandatory=true)
	public H header;
	@GedcomField
	public F records;
	@GedcomField
	public GedcomTrailer trailer;
}
