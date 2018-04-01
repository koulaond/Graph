package repository.schema.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TextProperty {

    String name() default "";

    boolean nonNull() default false;

    boolean immutable() default false;

    int minLength() default 0;

    int maxLength() default 65535;
}
