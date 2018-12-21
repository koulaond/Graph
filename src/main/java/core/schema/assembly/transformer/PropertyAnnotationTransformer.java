package core.schema.assembly.transformer;

import java.lang.annotation.Annotation;

import repository.api.definitions.property.PropertyDefinition;

public interface PropertyAnnotationTransformer<A extends Annotation> {

  PropertyDefinition transformToDefinition(A annotation, boolean multiValue);
}
