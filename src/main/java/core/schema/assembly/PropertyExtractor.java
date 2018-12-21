package core.schema.assembly;

import core.schema.annotations.properties.DateProperty;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;
import repository.api.definitions.property.PropertyDefinition;
import core.schema.assembly.transformer.PropertyAnnotationTransformer;
import core.schema.assembly.transformer.PropertyAnnotationTransformerProvider;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

@Slf4j
public class PropertyExtractor extends AbstractDefinitionExtractor<PropertyDefinition, Annotation> {

  private PropertyAnnotationTransformerProvider transformerProvider;

  public PropertyExtractor(Class<?> nodeClass) {
    super(nodeClass);
    this.transformerProvider = new PropertyAnnotationTransformerProvider();
  }

  @Override
  protected PropertyDefinition doTransform(Annotation annotation, Class<?> elementType, Class<?>... genericTypes) {
    PropertyAnnotationTransformer propertyAnnotationTransformer = transformerProvider.forPropertyAnnotation(annotation);
    return propertyAnnotationTransformer.transformToDefinition(annotation, isMultiValue(elementType));
  }

  @Override
  protected boolean isAnnotationOfType(Annotation annotation) {
    return DateProperty.class.equals(annotation.annotationType())
            || EnumProperty.class.equals(annotation.annotationType())
            || NumericProperty.class.equals(annotation.annotationType())
            || StringProperty.class.equals(annotation.annotationType());
  }
}
