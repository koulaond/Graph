package repository.schema.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation telling that the class describes the node schema.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Node {

    /**
     * Unique type of the node.
     */
    String nodeType() default "";

    /**
     * Indicator whether instance of this node type are immutable or not.
     */
    boolean immutable() default false;

    /**
     * Maximum count of the instances of this type that can be persisted in repository.
     */
    long maxCount() default Long.MAX_VALUE;
}
