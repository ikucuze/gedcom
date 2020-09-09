package fr.ikucuze.gedcom.access;

import java.util.List;

import fr.ikucuze.gedcom.structure.subrecord.ChildToFamilyLink;
import fr.ikucuze.gedcom.structure.subrecord.IndividualEventStructure;
import fr.ikucuze.gedcom.structure.subrecord.IndividualRecord;
import fr.ikucuze.gedcom.structure.subrecord.PersonalNamePieces;

public class Person {
	private final FamilyTree familyTree;
	private final IndividualRecord individual;
	
	public Person(FamilyTree familyTree, IndividualRecord individual) {
		this.familyTree = familyTree;
		this.individual = individual;
	}

	public String getXref() {
		return individual.xref;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb, "");
		return sb.toString();
	}

	void toString(StringBuilder sb, String indent) {
//		sb.append(indent);
//		sb.append("|-");
		sb.append(getName());
		sb.append("\n");
		
		Relation relation = getParents();
		if (relation!=null) {
			relation.toString(sb, indent);
		}
	}

	public Relation getParents() {
		if (individual.childToFamily!=null && ! individual.childToFamily.isEmpty()) {
			ChildToFamilyLink childToFamilyLink = individual.childToFamily.get(0);
			Relation relation = familyTree.mapRelations.get(childToFamilyLink.value);
			return relation;
		}
		return null;
	}
	
	public String getName() {
		return individual.name.get(0).value;
	}
	public String getFirstname() {
		PersonalNamePieces pieces = individual.name.get(0).pieces;
		return pieces==null?null:pieces.givenName;
	}
	public String getLastname() {
		PersonalNamePieces pieces = individual.name.get(0).pieces;
		return pieces==null?null:pieces.surname;
	}

	public enum SEX {
		Male("M"),
		Female("F"),
		Intersex("X"),
		Unknown("U"),
		Not_recorded("N");
		private String code;

		SEX(String code) {
			this.code = code;
		}
		
		public static SEX forCode(String c) {
			for (SEX s : values()) {
				if (s.code.equals(c)) {
					return s;
				}
			}
			return null;
		}
	}
	
	public SEX getSex() {
		return SEX.forCode(individual.sex);
	}

	public String getBirth() {
		if (individual.events==null) {
			return null;
		}
		for (IndividualEventStructure event : individual.events) {
			if (event.birth!=null && event.birth.date!=null) {
				return event.birth.date.value;
			}
		}
		return null;
	}
	public String getDeath() {
		if (individual.events==null) {
			return null;
		}
		for (IndividualEventStructure event : individual.events) {
			if (event.death!=null && event.death.date!=null) {
				return event.death.date.value;
			}
		}
		return null;
	}
	
	public boolean isSameRef(Person p) {
		if (p==null) {
			return false;
		}
		return getXref().equals(p.getXref());
	}
}
