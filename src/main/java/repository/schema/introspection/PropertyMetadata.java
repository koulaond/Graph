package repository.schema.introspection;

import java.lang.annotation.Annotation;

public class PropertyMetadata<A extends Annotation> {

    private A propertyAnnotation;
    private Class propertyType;

    public PropertyMetadata(A propertyAnnotation, Class propertyType) {
        this.propertyAnnotation = propertyAnnotation;
        this.propertyType = propertyType;
    }

    public A getPropertyAnnotation() {
        return propertyAnnotation;
    }

    public Class getPropertyType() {
        return propertyType;
    }
}
