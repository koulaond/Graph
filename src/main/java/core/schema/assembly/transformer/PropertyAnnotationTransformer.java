package core.schema.assembly.transformer;

import java.lang.annotation.Annotation;

import core.schema.assembly.definitions.property.PropertyDefinition;

public interface PropertyAnnotationTransformer<T extends Annotation> {

  PropertyDefinition transformToDefinition(T annotation, boolean multiValue);
}
