package repository.schema.annotations.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface StringProperty {

    String name() default "";

    boolean nonNull() default false;

    boolean immutable() default false;

    int minLength() default 0;

    int maxLength() default 65535;
}
