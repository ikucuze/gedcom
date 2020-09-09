package fr.ikucuze.gedcom.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import fr.ikucuze.gedcom.structure.annotation.GedcomEnum;

public class Factory {
	
	public static interface GedComStorage {
		public void setValue(String value) throws IllegalArgumentException, IllegalAccessException, InstantiationException;
		public GedcomObject getStorageItem();
		
		public static GedComStorage onItem(final GedcomObject item) {
			return new GedComStorage() {
				@Override
				public void setValue(String value) {
					item.setValue( value );
				}
				@Override
				public GedcomObject getStorageItem() {
					return item;
				}
			};
		}
		public static GedComStorage onField(final GedcomObject item, final Field declaredField) {
			return new GedComStorage() {
				@Override
				public void setValue(String value) throws IllegalArgumentException, IllegalAccessException {
					if (declaredField.get(item)!=null) {
						throw new RuntimeException("field already set: "+declaredField.getName()+ " on "+item.getClass());
					}
					declaredField.set(item, value);
				}
				@Override
				public GedcomObject getStorageItem() {
					return null;
				}
			};
		}
		public static GedComStorage onEnum(final GedcomObject item, final Field declaredField) {
			return new GedComStorage() {
				@Override
				public void setValue(String value) throws IllegalArgumentException, IllegalAccessException {
					if (declaredField.get(item)!=null) {
						throw new RuntimeException("field already set: "+declaredField.getName()+ " on "+item.getClass());
					}

					Class<GedcomEnum> clazz = (Class<GedcomEnum>) declaredField.getType();
					GedcomEnum[] enumConstants = clazz.getEnumConstants();
					GedcomEnum enumToSet = null;
					for (GedcomEnum e : enumConstants) {
						if (e.getGedcomValue().equals(value)) {
							enumToSet = e;
							break;
						}
					}
					if (enumToSet==null) {
						throw new RuntimeException("value is not allowed : "+value + " in " + declaredField.getName()+ " on "+item.getClass());
					}
					
					declaredField.set(item, enumToSet);
				}
				@Override
				public GedcomObject getStorageItem() {
					return null;
				}
			};
		}
		public static GedComStorage onListOfString(final List<String> liste) {
			return new GedComStorage() {
				@Override
				public void setValue(String value) throws IllegalArgumentException, IllegalAccessException {
					((List<String>)liste).add(value);						
				}
				@Override
				public GedcomObject getStorageItem() {
					return null;
				}
			};
		}
	}
	
	public static GedComStorage getValueStorageOnItem(GedcomObject gedComObject, List<Field> path) throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		Field declaredField = path.remove(0);
		Class<?> clazz = declaredField.getType();

		if ( GedcomObject.class.isAssignableFrom( clazz ) ) {
			GedcomObject item = (GedcomObject) declaredField.get(gedComObject);
			if (path.isEmpty()) {
				GedcomObject newItem = Factory.getNewGedcom(gedComObject, declaredField);
				return GedComStorage.onItem( newItem );
			} else {
				if (item == null) {
					item = Factory.getNewGedcom(gedComObject, declaredField);
				}
				return getValueStorageOnItem(item, path);
			}
		} else if ( List.class.isAssignableFrom( clazz ) ) {
			final List<?> liste = safeGetListField(gedComObject, declaredField);
			// list of what ?
			ParameterizedType genericType = (ParameterizedType) declaredField.getGenericType();
			Class<?> type = (Class<?>) genericType.getActualTypeArguments()[0];
			if (String.class.isAssignableFrom(type)) {
				if ( ! path.isEmpty() ) {
					throw new RuntimeException("Tag access path goes through non-Gedcom object!");
				}
				return GedComStorage.onListOfString((List<String>) liste);
			} else {
				GedcomObject newItem = (GedcomObject) type.newInstance();
				((List<GedcomObject>)liste).add(newItem);
				if (path.isEmpty()) {
					return GedComStorage.onItem(newItem);
				} else {
					return getValueStorageOnItem(newItem, path);
				}
			}
		} else {
			if ( ! path.isEmpty() ) {
				throw new RuntimeException("Tag access path goes through non-Gedcom object!");
			}
			if ( String.class.isAssignableFrom( clazz ) ) {
				return GedComStorage.onField(gedComObject, declaredField);
			} else if (Enum.class.isAssignableFrom(clazz)) {
				return GedComStorage.onEnum(gedComObject, declaredField);
			} else {
				throw new RuntimeException("unsupported field: "+declaredField.getName() + " : " +gedComObject.getClass());
			}
		}
	}

	private static List<?> safeGetListField(GedcomObject gedComObject, Field declaredField)
			throws IllegalAccessException, InstantiationException {
		List<?> liste = (List<?>) declaredField.get(gedComObject);
		if (declaredField.get(gedComObject)==null) {
			liste = new ArrayList<>();
			declaredField.set(gedComObject, liste);
		}
		return liste;
	}
	
	private static GedcomObject getNewGedcom(GedcomObject gedComObject, Field declaredField) throws IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		GedcomObject newItem = (GedcomObject) declaredField.get(gedComObject);
		if (newItem==null) {
			Class<GedcomObject> type = (Class<GedcomObject>) declaredField.getType();
			
			Type genericType = declaredField.getGenericType();
			if (genericType!=type) {
				String typeName = genericType.getTypeName();
				
				ParameterizedType parameterizedType = (ParameterizedType) gedComObject.getClass().getGenericSuperclass();
				Class<?> rawType = (Class) parameterizedType.getRawType();
				TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
				for (int i=0;i<typeParameters.length;i++) {
					if (typeParameters[i].getName().equals(typeName)) {
						String realTypeName = parameterizedType.getActualTypeArguments()[i].getTypeName();
						type = (Class<GedcomObject>) Class.forName(realTypeName);
						break;
					}
				}
			}
			
			newItem = type.newInstance();
			declaredField.set(gedComObject, newItem);
		} else {
			throw new RuntimeException("Field already filled! "+declaredField.getName() + " : " +declaredField.getType());
		}
		return newItem;
	}

}
