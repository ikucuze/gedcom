package fr.ikucuze.gedcom.structure.toplevel;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class GedcomTrailer extends GedcomObject {
	@GedcomField(name="TRLR",mandatory=true)
	public GedcomObject trailer;
}
