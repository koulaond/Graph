package repository.schema.introspection;

import repository.schema.descriptions.PropertyDescription;

import java.lang.annotation.Annotation;

public interface PropertyDescriptionProcessor<PD extends PropertyDescription, A extends Annotation> {

    PD processProperty(A propertyAnnotation, boolean multiValue);

}
