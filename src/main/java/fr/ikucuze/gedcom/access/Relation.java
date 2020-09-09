package fr.ikucuze.gedcom.access;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.ikucuze.gedcom.structure.primitive.DateValue.Date;
import fr.ikucuze.gedcom.structure.subrecord.FamGroupRecord;

public class Relation {

	public static final Comparator<Relation> comparatorByDate = new Comparator<Relation>() {
		@Override
		public int compare(Relation o1, Relation o2) {
			Optional<Date> optoldestDate1 = o1.family.events.stream()
					.filter(e->e!=null&&e.event!=null&&e.event.date!=null)
					.map(e->e.event.date.getDate1())
					.filter(d->d!=null)					
					.min(Date.comparator);
			Date oldestDate1 = optoldestDate1.isPresent()?optoldestDate1.get():null;
			
			Optional<Date> optoldestDate2 = o2.family.events.stream()
					.filter(e->e!=null&&e.event!=null&&e.event.date!=null)
					.map(e->e.event.date.getDate1())
					.filter(d->d!=null)					
					.min(Date.comparator);
			Date oldestDate2 = optoldestDate2.isPresent()?optoldestDate2.get():null;
			
			return oldestDate1==null?(oldestDate2==null?0:-1):oldestDate1.compareTo(oldestDate2);
		}
	};

	private FamilyTree familyTree;
	private FamGroupRecord family;

	/**
	 * @param familyTree
	 * @param family
	 */
	public Relation(FamilyTree familyTree, FamGroupRecord family) {
		this.familyTree = familyTree;
		this.family = family;
	}

	public String getXref() {
		return family.xref;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb, "");
		return sb.toString();
	}
	void toString(StringBuilder sb, String indent) {
		Person husband = getHusband();
		Person wife = getWife();
		if (husband!=null) {
			sb.append(indent);
			sb.append((wife==null?" \\- ":" |- "));
			husband.toString(sb, indent+(wife==null?"    ":" |  "));
		}
		if (wife!=null) {
			sb.append(indent);
			sb.append(" \\- ");
			wife.toString(sb, indent+"    ");
		}
	}

	public Person getHusband() {
		return family.husband==null?null:familyTree.mapPersons.get(family.husband);
	}
	public Person getWife() {
		return family.wife==null?null:familyTree.mapPersons.get(family.wife);
	}
	public List<Person> getChildren() {
		if (family.children==null) {
			return Collections.emptyList();
		}
		return family.children.stream().map(c -> familyTree.mapPersons.get(c)).collect(Collectors.toList());
	}

}
