package repository.schema.introspection;

import repository.schema.annotations.Node;

import java.lang.annotation.Annotation;

import static java.lang.String.format;
import static repository.schema.introspection.Constants.ERROR_DUPLICATE_ANNOTATIONS;
import static repository.schema.introspection.Constants.ERROR_MISSING_ANNOTATION;

public abstract class Introspector<T, A extends Annotation, RT> {

    protected Class<T> introspectedClass;

    public Introspector(Class<T> introspectedClass) {
        this.introspectedClass = introspectedClass;
    }

    /**
     * Validates whether the given annotation is declared on the introspected class.
     * Throws {@link IllegalArgumentException} when an annotation is not declared or if an annotation is duplicate.
     * @param annotationClass annotation class
     */
    public void validateClass(Class<A> annotationClass) {
        A[] nodeAnnotations = introspectedClass.getAnnotationsByType(annotationClass);
        if (nodeAnnotations.length == 0) {
            throw new IllegalStateException(format(ERROR_MISSING_ANNOTATION, annotationClass.getName(), introspectedClass.getName()));
        }
        if (nodeAnnotations.length > 1) {
            throw new IllegalStateException(format(ERROR_DUPLICATE_ANNOTATIONS, annotationClass.getName(), introspectedClass.getName()));
        }
    }

    /**
     * Introspects class and returns the result.
     * @return introspection result
     */
    public abstract RT introspect();
}