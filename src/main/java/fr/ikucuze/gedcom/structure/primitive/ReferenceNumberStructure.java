package fr.ikucuze.gedcom.structure.primitive;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class ReferenceNumberStructure extends GedcomObject {
	@GedcomField(name="TYPE")
	public String type;
}
