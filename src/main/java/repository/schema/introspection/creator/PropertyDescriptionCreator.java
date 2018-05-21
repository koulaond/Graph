package repository.schema.introspection.creator;

import repository.schema.descriptions.PropertyDescription;

import java.lang.annotation.Annotation;

/**
 * Property description creator interface defining behaviour for processing property (and relation) annotations values
 * and return result description instance with these values.
 *
 * @param <PD> property/relation description type
 * @param <A>  property/relation annotation type
 */
public interface PropertyDescriptionCreator<PD extends PropertyDescription, A extends Annotation> {

    PD processProperty(A annotation, String fieldName, boolean multiValue);

    default String resolveName(String annotationNameAttr, String fieldName) {
        return (annotationNameAttr == null || annotationNameAttr.isEmpty()) ? fieldName : annotationNameAttr;
    }
}
