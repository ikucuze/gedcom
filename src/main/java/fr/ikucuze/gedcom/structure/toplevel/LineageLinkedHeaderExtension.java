package fr.ikucuze.gedcom.structure.toplevel;

import java.util.List;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.subrecord.AddressStructure;
import fr.ikucuze.gedcom.structure.subrecord.NoteStructure;

public class LineageLinkedHeaderExtension extends GedcomHeaderExtension {
	public static class Source extends GedcomObject {
		@GedcomField(name="NAME")
		public String name;
		@GedcomField(name="VERS")
		public String vers;
		@GedcomField(name="CORP")
		public AddressStructure corp;
		public static class Data extends GedcomObject {
			@GedcomField(name="DATE")
			public String date;
			@GedcomField(name="COPR")
			public String copyright;
		}
		@GedcomField(name="DATA")
		public Data data;
	}
	public static class Date extends GedcomObject {
		@GedcomField(name="TIME")
		public String time;
	}

	@GedcomField(name="DEST", min=1, max=20)
	public String destination;
	@GedcomField(name="SOUR", min=1, max=20)
	public Source source;
	@GedcomField(name="DATE")
	public Date date;
	@GedcomField(name="LANG")
	public String language;
	@GedcomField(name="SUBM")
	public List<SubmitterRecord> submitters;
	@GedcomField(name="FILE")
	public String file;
	@GedcomField(name="COPR")
	public String copyright;
	@GedcomField
	public NoteStructure note;
}
