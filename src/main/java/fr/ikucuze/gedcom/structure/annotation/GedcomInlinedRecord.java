package fr.ikucuze.gedcom.structure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GedcomInlinedRecord {
	public String name() default "";
	public boolean mandatoryValue() default false;
}
