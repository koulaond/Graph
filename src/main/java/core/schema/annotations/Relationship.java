package core.schema.annotations;

import model.Direction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Relation annotation describing relation between two node types
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Relationship {

    String name();

    boolean nonNull() default true;

    boolean immutable() default false;

    Direction direction() default Direction.UNDIRECTED;

    Class<?> propertyHolderClass() default EmptyHolder.class;

    class EmptyHolder {
        // Just default empty holder class that does not describe any property
    }
}
