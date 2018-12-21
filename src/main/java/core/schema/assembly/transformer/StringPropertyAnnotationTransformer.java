package core.schema.assembly.transformer;

import core.schema.annotations.properties.StringProperty;
import repository.api.definitions.property.StringPropertyDefinition;

public class StringPropertyAnnotationTransformer implements PropertyAnnotationTransformer<StringProperty> {
  @Override
  public StringPropertyDefinition transformToDefinition(StringProperty annotation, boolean multiValue) {
    return new StringPropertyDefinition(
        annotation.name(),
        annotation.nonNull(),
        multiValue,
        annotation.immutable(),
        annotation.minLength(),
        annotation.maxLength());
  }
}
