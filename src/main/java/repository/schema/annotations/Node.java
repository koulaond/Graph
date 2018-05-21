package repository.schema.annotations;

import java.lang.annotation.*;

/**
 * Annotation telling that the class describes the node schema.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
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
