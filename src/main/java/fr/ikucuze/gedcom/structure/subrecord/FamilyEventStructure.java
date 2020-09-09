package fr.ikucuze.gedcom.structure.subrecord;

import java.lang.reflect.Field;

import fr.ikucuze.gedcom.structure.GedcomObject;
import fr.ikucuze.gedcom.structure.annotation.GedcomField;

public class FamilyEventStructure extends GedcomObject {
	@GedcomField(name="ANUL")
	public FamilyEventDetail annulment;
	@GedcomField(name="CENS")
	public FamilyEventDetail census;
	@GedcomField(name="DIV")
	public FamilyEventDetail divorce;
	@GedcomField(name="DIVF")
	public FamilyEventDetail divorceFilling;
	@GedcomField(name="ENGA")
	public FamilyEventDetail engagement;
	@GedcomField(name="MARB")
	public FamilyEventDetail marriageBann;
	@GedcomField(name="MARC")
	public FamilyEventDetail marriageContract;
	@GedcomField(name="MARR")
	public FamilyEventDetail marriage;
	@GedcomField(name="MARL")
	public FamilyEventDetail marriageLicence;
	@GedcomField(name="MARS")
	public FamilyEventDetail marriageSettlement;
	@GedcomField(name="RESI")
	public FamilyEventDetail residence;
	@GedcomField(name="EVEN")
	public FamilyEventDetail event;
	
	public FamilyEventDetail getEventItem() {
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(GedcomField.class) && 
					field.getType().isAssignableFrom(FamilyEventDetail.class))
			{
//				FamilyEventDetail object = (FamilyEventDetail) field.get(this);
//				if (object!=null) {
//					return object;
//				}
			}
		}
		return null;
	}
}
