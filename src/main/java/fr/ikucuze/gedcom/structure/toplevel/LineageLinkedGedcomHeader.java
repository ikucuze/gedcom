package fr.ikucuze.gedcom.structure.toplevel;

import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="HEAD")
public class LineageLinkedGedcomHeader extends GedcomHeader {
	@GedcomField
	public LineageLinkedHeaderExtension extension;
}
