package repository.schema.annotations;

import repository.schema.Direction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Relation {

    String type();

    Direction direction() default Direction.UNDIRECTED;

    Class<?> propertyHolderClass();
}
