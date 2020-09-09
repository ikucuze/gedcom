package fr.ikucuze.gedcom.structure.subrecord;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

@GedcomInlinedRecord(name="REPO")
public class SourceRepositoryCitation extends GedcomObject {
	public static class CallNumber extends GedcomObject {
		@GedcomField(name="MEDI")
		public String mediaType;
	}
	
	@GedcomField(name="CALN")
	public CallNumber callNumber;
}
