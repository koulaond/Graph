package core.schema.introspection.creator;

import core.schema.descriptions.Description;

import java.lang.annotation.Annotation;

/**
 * Property description creator interface defining behaviour for processing property (and relation) annotations values
 * and return result description instance with these values.
 *
 * @param <PD> property/relation description type
 * @param <A>  property/relation annotation type
 */
public interface DescriptionCreator<PD extends Description, A extends Annotation> {

    PD processProperty(A annotation, String fieldName, boolean multiValue);

    default String resolveName(String annotationNameAttr, String fieldName) {
        return (annotationNameAttr == null || annotationNameAttr.isEmpty()) ? fieldName : annotationNameAttr;
    }
}
