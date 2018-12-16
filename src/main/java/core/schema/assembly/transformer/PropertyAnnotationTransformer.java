package core.schema.assembly.transformer;

import java.lang.annotation.Annotation;

import core.schema.assembly.definitions.property.PropertyDefinition;

public interface PropertyAnnotationTransformer<A extends Annotation> {

  PropertyDefinition transformToDefinition(A annotation, boolean multiValue);
}
