package repository.schema.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NumericProperty {

    String name() default "";

    boolean nonNull() default false;

    boolean immutable() default false;

    long minValue() default Long.MIN_VALUE;

    long maxValue() default Long.MAX_VALUE;
}
