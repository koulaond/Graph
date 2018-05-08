package repository.schema.introspection.processor;

import repository.schema.descriptions.PropertyDescription;

import java.lang.annotation.Annotation;

public interface PropertyDescriptionProcessor<PD extends PropertyDescription, A extends Annotation> {

    PD processProperty(A annotation, boolean multiValue);
}
