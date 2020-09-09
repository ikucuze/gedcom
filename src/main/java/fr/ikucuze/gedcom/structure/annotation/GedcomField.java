package fr.ikucuze.gedcom.structure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GedcomField {
	public String name() default "";
	public boolean mandatory() default false;
	public int min() default 0;
	public int max() default Integer.MAX_VALUE;
	public String regexp() default "";
}
