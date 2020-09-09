package fr.ikucuze.gedcom.structure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GedcomPrimitive {
	public boolean mandatory() default false;
	public String regexp() default "";
}
