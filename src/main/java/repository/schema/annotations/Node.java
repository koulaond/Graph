package repository.schema.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Node {

    String nodeType() default "";

    boolean immutable() default false;

    long maxCount() default Long.MAX_VALUE;
}
