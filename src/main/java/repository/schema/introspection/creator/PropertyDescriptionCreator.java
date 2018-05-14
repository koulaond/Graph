package repository.schema.introspection.creator;

import repository.schema.descriptions.PropertyDescription;

import java.lang.annotation.Annotation;

public interface PropertyDescriptionCreator<PD extends PropertyDescription, A extends Annotation> {

    PD processProperty(A annotation, boolean multiValue);
}
