package core.schema.annotations.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Property annotation describing enumeration property persisted in a node/relation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EnumProperty {

    String name();

    boolean nonNull() default false;

    boolean immutable() default false;

    Class enumClass();
}
