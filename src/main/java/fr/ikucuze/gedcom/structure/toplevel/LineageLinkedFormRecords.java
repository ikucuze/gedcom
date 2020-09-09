package fr.ikucuze.gedcom.structure.toplevel;

import java.util.List;

import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class LineageLinkedFormRecords extends GedcomFormRecords {
	@GedcomField(mandatory=true)
	public SubmitterRecord submitter;
	@GedcomField(name="")
	public List<LineageLinkedRecord> lineageLinkedRecords;

}
