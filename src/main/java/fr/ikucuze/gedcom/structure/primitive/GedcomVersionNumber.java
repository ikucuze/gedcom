package fr.ikucuze.gedcom.structure.primitive;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomPrimitive;

@GedcomPrimitive(regexp="\\d{1,3}\\.\\d{1,3}(?:\\.\\d{1,3})?")
public final class GedcomVersionNumber extends GedcomObject {
	/*
MMM + dot + mmm [ + dot + rrr ]
where:
MMM = 1 through 3 digits; the major version number
mmm = 1 through 3 digits; the minor version number
rrr = 1 through 3 digits; the revision number
dot = (2E), the full stop character
The version number of the specification used
	 */
	public String getVersion() {
		return this.value;
	}

}
