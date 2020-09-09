package fr.ikucuze.gedcom.structure.subrecord;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

// seen in exemple
@Deprecated
public class CommentsWithCont extends GedcomObject {
	@GedcomField(name="CONT")
	public List<String> continues;
}
