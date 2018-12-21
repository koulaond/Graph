package core.schema.assembly.transformer;

import java.util.Set;
import java.util.stream.Stream;

import core.schema.annotations.properties.EnumProperty;
import repository.api.definitions.property.EnumPropertyDefinition;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

public class EnumPropertyAnnotationTransformer implements PropertyAnnotationTransformer<EnumProperty> {

  @Override
  public EnumPropertyDefinition transformToDefinition(EnumProperty annotation, boolean multiValue) {
    Class enumClass = annotation.enumClass();
    Object[] enumConstants = enumClass.getEnumConstants();
    if(enumConstants == null) {
      throw new IllegalArgumentException(format("Enum constants are not present, maybe declared class %s is not enum type.", enumClass.getName()));
    }
    Set<String> enumValues = Stream.of(enumConstants).map(enumConstant -> enumConstant.toString()).collect(toSet());
    return new EnumPropertyDefinition(annotation.name(), annotation.nonNull(), multiValue, annotation.immutable(), enumValues);
  }
}
