package core.schema.assembly;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import core.schema.annotations.properties.DateProperty;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;
import core.schema.assembly.definitions.property.PropertyDefinition;
import core.schema.assembly.transformer.PropertyAnnotationTransformer;
import core.schema.assembly.transformer.PropertyAnnotationTransformerProvider;
import lombok.extern.slf4j.Slf4j;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Slf4j
public class PropertyExtractor {

  private PropertyAnnotationTransformerProvider transformerProvider;

  public PropertyExtractor() {
    this.transformerProvider = new PropertyAnnotationTransformerProvider();
  }

  public Set<PropertyDefinition> extractProperties(Class<?> nodeClass) {
    Set<PropertyDefinition> propertyDefinitions = new HashSet<>();
    while (!nodeClass.equals(Object.class)) {
      Set<PropertyDefinition> oneLevelDefinitions = findDefinitions(nodeClass);
      oneLevelDefinitions.forEach(propertyDefinition -> {
        if (!propertyDefinitions.contains(propertyDefinition)) {
          propertyDefinitions.add(propertyDefinition);
        }
      });
      nodeClass = nodeClass.getSuperclass();
    }
    return propertyDefinitions;
  }

  private Set<PropertyDefinition> findDefinitions(Class<?> nodeClass) {
    Set<PropertyDefinition> oneLevelDefinitions = new HashSet<>();

    // 1. Collect all property annotations from accessor methods level
    Method[] methods = nodeClass.getDeclaredMethods();
    if (methods != null && methods.length > 0) {
      Stream.of(methods)
          .filter(method -> !Void.class.equals(method.getReturnType()))
          .forEach(method -> {
            Annotation[] annotationsOnMethod = method.getDeclaredAnnotations();
            PropertyDefinition propertyDefinitionOnMethod = transformAnnotationsToDefinitions(annotationsOnMethod, method.getName(), method.getReturnType());
            if (propertyDefinitionOnMethod != null) {
              if (oneLevelDefinitions.contains(propertyDefinitionOnMethod)) {
                log.warn("Property definition with name {} is already defined in class. Overwriting.",
                    propertyDefinitionOnMethod.getPropertyName());
              }
              oneLevelDefinitions.add(propertyDefinitionOnMethod);
            }
          });
    }

    // 2. Collect all property annotations from fields level
    Field[] fields = nodeClass.getDeclaredFields();
    if (fields != null && fields.length > 0) {
      Stream.of(fields)
          .forEach(field -> {
            Annotation[] annotationsOnField = field.getDeclaredAnnotations();
            PropertyDefinition propertyDefinitionOnField = transformAnnotationsToDefinitions(annotationsOnField, field.getName(), field.getType());
            if (propertyDefinitionOnField != null) {
              if (oneLevelDefinitions.contains(propertyDefinitionOnField)) {
                log.warn("Property annotation {} on field {} is already defined on one of accessory methods, skipping this annotation.",
                    propertyDefinitionOnField.getPropertyName(),
                    field.getName());
              } else {
                oneLevelDefinitions.add(propertyDefinitionOnField);
              }
            }
          });
    }

    return oneLevelDefinitions;
  }

  private PropertyDefinition transformAnnotationsToDefinitions(Annotation[] annotations, String elementName, Class<?> elementType) {
    if (annotations == null || annotations.length == 0) {
      return null;
    }
    List<Annotation> propertyAnnotations = Stream.of(annotations).filter(this::isPropertyAnnotation).collect(toList());
    if (propertyAnnotations.size() > 1) {
      throw new IllegalStateException(format("More than one property annotation is present on declared method %s.", elementName));
    }
    if (propertyAnnotations.size() == 1) {
      Annotation propertyAnnotation = propertyAnnotations.get(0);
      PropertyAnnotationTransformer propertyAnnotationTransformer = transformerProvider.forPropertyAnnotation(propertyAnnotation);
      return propertyAnnotationTransformer.transformToDefinition(propertyAnnotation, isMultiValue(elementType));
    }
    return null;
  }

  private boolean isPropertyAnnotation(Annotation annotation) {
    return DateProperty.class.equals(annotation.annotationType())
        || EnumProperty.class.equals(annotation.annotationType())
        || NumericProperty.class.equals(annotation.annotationType())
        || StringProperty.class.equals(annotation.annotationType());
  }

  private boolean isMultiValue(Class<?> clazz) {
    Class<?> componentType = clazz.getComponentType();
    if (componentType != null) {
      // Class is an array
      return true;
    }
    // If class is a collection or map type then is multiValue
    return Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz);
  }
}
