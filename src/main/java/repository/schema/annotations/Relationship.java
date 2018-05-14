package repository.schema.annotations;

import repository.schema.Direction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Relation annotation describing relation between two node types
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Relationship {

    String name();

    boolean nonNull() default true;

    boolean immutable() default false;

    Direction direction() default Direction.UNDIRECTED;

    Class<?> propertyHolderClass();

    Class<?> referencedClass();
}
