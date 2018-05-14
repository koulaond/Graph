package repository.schema.annotations.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Property annotation describing date property persisted in a node/relation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface DateProperty {

    String name() default "";

    boolean nonNull() default false;

    boolean immutable() default false;

    String format() default "YYYY-MM-DD hh:mm:ss.s";
}
