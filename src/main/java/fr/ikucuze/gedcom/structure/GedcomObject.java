package fr.ikucuze.gedcom.structure;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.ikucuze.gedcom.structure.annotation.GedcomField;
import fr.ikucuze.gedcom.structure.annotation.GedcomInlinedRecord;

public class GedcomObject {
	private static Map<Class<? extends GedcomObject>,Map<String,List<Field>>> fieldsByClasses =
			new HashMap<>();
	
	public String xref;
	public String value;
	// unknown attributes only
	public Map<String,GedcomObject> gedcomAttributes = new LinkedHashMap<>();

	public void addGedComAttrib(String field, GedcomObject attribute) {
		gedcomAttributes.put(field, attribute);
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public List<Field> findField(String field) {
		initFields();
		List<Field> list = fieldsByClasses.get(getClass()).get(field);
		return list==null?null:Collections.unmodifiableList( list );
	}

	private Map<String, List<Field>> initFields() {
		return initFields(getClass());
	}
	
	private static Map<String, List<Field>> initFields(Class<? extends GedcomObject> clazz) {
		Map<String, List<Field>> fields = fieldsByClasses.get(clazz);
		if (fields==null) {
			fields = new LinkedHashMap<>();
			readTagNamesFromClass(clazz, fields);
			fieldsByClasses.put(clazz, fields);
		}
		return fields;
	}
	
	private static void readTagNamesFromClass(Class<? extends GedcomObject> clazz, Map<String,List<Field>> fields) {
		List<Field> fieldsOfClass = listFields(clazz);
		for (Field field : fieldsOfClass) {
			if (field.isAnnotationPresent(GedcomField.class)) {
				GedcomField annotation = field.getAnnotation(GedcomField.class);
				String tag = annotation.name();
				if (tag.isEmpty()) {
					// inlined tag
					Class<?> inlined = field.getType();
					boolean isList = false;
					if ( List.class.isAssignableFrom(inlined) ) {
						ParameterizedType genericType = (ParameterizedType) field.getGenericType();
						Class<?> type = (Class<?>) genericType.getActualTypeArguments()[0];
						if (GedcomObject.class.isAssignableFrom(type)) {
							inlined = type;
						}
						isList = true;
					}
					if ( GedcomObject.class.isAssignableFrom(inlined) ) {
						if (inlined.isAnnotationPresent(GedcomInlinedRecord.class)) {
							GedcomInlinedRecord annotationClass = (GedcomInlinedRecord) inlined.getAnnotation(GedcomInlinedRecord.class);
							if ( ! tag.isEmpty()) {
								throw new RuntimeException("Inline tag with tag already declared: "+tag+ " in "+clazz);
							}
							tag = annotationClass.name();
						}
						if ( ! tag.isEmpty() ) {
							if (fields.containsKey(tag)) {
								throw new RuntimeException("Tag name '" + tag + "' is defined twice in type "+clazz);
							}
							fields.put(tag, Collections.singletonList( field ) );
						} else {
							if ( ! isList && field.getGenericType()!=null) {
								String genericName = field.getGenericType().getTypeName();
								TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
								if (typeParameters.length==0 && clazz.getGenericSuperclass() instanceof ParameterizedType) {
									typeParameters = ((Class<?>)((ParameterizedType)clazz.getGenericSuperclass()).getRawType()).getTypeParameters();
								}
								for (int i=0;i<typeParameters.length;i++) {
									TypeVariable<?> typeVariable = typeParameters[i];
									if (typeVariable.getName().equals(genericName)) {
//										clazz.get
										Type actualType = ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments()[i];
										inlined = (Class<?>)actualType;
										break;
									}
								}
							}
							
							Map<String, List<Field>> inlinedFields = initFields((Class<? extends GedcomObject>) inlined);
							for (Entry<String, List<Field>> entry : inlinedFields.entrySet() ) {
								String inlinedTag = entry.getKey();
								if (fields.containsKey(inlinedTag)) {
									throw new RuntimeException("Tag name '" + inlinedTag + "' is defined twice in type "+clazz);
								}
								ArrayList<Field> path = new ArrayList<>();
								path.add(field);
								path.addAll(entry.getValue());
								fields.put(inlinedTag, path );
							}
						}
					} else {
						throw new RuntimeException("Can only inline gedcomobject : "+field);
					}
				} else {
					if (fields.containsKey(tag)) {
						throw new RuntimeException("Tag name '" + tag + "' is defined twice in type "+clazz);
					}
					fields.put(tag, Collections.singletonList( field ) );
				}
			}
		}
	}

	private static List<Field> listFields(Class<? extends GedcomObject> clazz) {
		List<Field> listFields = new ArrayList<>();
		if ( ! clazz.isAssignableFrom(GedcomObject.class)) {
			listFields.addAll( listFields((Class<? extends GedcomObject>) clazz.getSuperclass()) );
			listFields.addAll( Arrays.asList(clazz.getDeclaredFields()) );
		}
		return listFields;
	}

	public Map<String, List<Field>> getFieldsList() {
		return initFields();
	}

}
