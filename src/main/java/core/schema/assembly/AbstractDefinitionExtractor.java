package core.schema.assembly;

import repository.api.definitions.AbstractDefinition;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Slf4j
public abstract class AbstractDefinitionExtractor<T extends AbstractDefinition, A extends Annotation> {

  protected Class<?> nodeClass;

  public AbstractDefinitionExtractor(Class<?> nodeClass) {
    this.nodeClass = nodeClass;
  }

  public Set<T> extractDefinitions() {
    Set<T> definitions = new HashSet<>();
    while (!nodeClass.equals(Object.class)) {
      Set<T> oneLevelDefinitions = findDefinitions(nodeClass);
      oneLevelDefinitions.forEach(propertyDefinition -> {
        if (!definitions.contains(propertyDefinition)) {
          definitions.add(propertyDefinition);
        }
      });
      nodeClass = nodeClass.getSuperclass();
    }
    return definitions;
  }

  protected Set<T> findDefinitions(Class<?> nodeClass) {
    Set<T> oneLevelDefinitions = new HashSet<>();

    // 1. Collect all property annotations from accessor methods level
    Method[] methods = nodeClass.getDeclaredMethods();
    if (methods != null && methods.length > 0) {
      Stream.of(methods)
              .filter(method -> !Void.class.equals(method.getReturnType()))
              .forEach(method -> {
                Annotation[] annotationsOnMethod = method.getDeclaredAnnotations();
                Type methodReturnType = method.getGenericReturnType();
                T propertyDefinitionOnMethod = null;
                if (methodReturnType instanceof ParameterizedType) {
                  Type[] methodReturnTypeGenerics = ((ParameterizedType) methodReturnType).getActualTypeArguments();
                  if (methodReturnTypeGenerics.length > 0) {
                    Type genericParameterType = methodReturnTypeGenerics[0];
                    String typeName = genericParameterType.getTypeName();
                    try {
                      Class<?> genericTypeClass = Class.forName(typeName);
                      propertyDefinitionOnMethod = transformAnnotationToDefinition(annotationsOnMethod, method.getName(), method.getReturnType(), genericTypeClass);
                    } catch (ClassNotFoundException e) {
                      log.error("Cannot resolve class for type {}", typeName);
                      throw new IllegalStateException();
                    }
                  } else {
                    propertyDefinitionOnMethod = transformAnnotationToDefinition(annotationsOnMethod, method.getName(), method.getReturnType());
                  }
                }
                if (propertyDefinitionOnMethod != null) {
                  if (oneLevelDefinitions.contains(propertyDefinitionOnMethod)) {
                    log.warn("Property definition with name {} is already defined in class. Overwriting.",
                            propertyDefinitionOnMethod.getName());
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
                Type genericType = field.getGenericType();
                T propertyDefinitionOnField = null;
                if (genericType instanceof ParameterizedType) {
                  Type[] fieldTypeGenerics = ((ParameterizedType) genericType).getActualTypeArguments();
                  if (fieldTypeGenerics.length > 0) {
                    String typeName = fieldTypeGenerics[0].getTypeName();
                    try {
                      Class<?> genericTypeClass = Class.forName(typeName);
                      propertyDefinitionOnField = transformAnnotationToDefinition(annotationsOnField, field.getName(), field.getType(), genericTypeClass);
                    } catch (ClassNotFoundException e) {
                      log.error("Cannot resolve class for type {}", typeName);
                      throw new IllegalStateException();
                    }
                  }
                } else {
                  propertyDefinitionOnField = transformAnnotationToDefinition(annotationsOnField, field.getName(), field.getType());
                }
                if (propertyDefinitionOnField != null) {
                  if (oneLevelDefinitions.contains(propertyDefinitionOnField)) {
                    log.warn("Property annotation {} on field {} is already defined on one of accessory methods, skipping this annotation.",
                            propertyDefinitionOnField.getName(),
                            field.getName());
                  } else {
                    oneLevelDefinitions.add(propertyDefinitionOnField);
                  }
                }
              });
    }
    return oneLevelDefinitions;
  }


  private T transformAnnotationToDefinition(Annotation[] annotations, String elementName, Class<?> elementType, Class<?>... genericTypes) {
    if (annotations == null || annotations.length == 0) {
      return null;
    }
    List<A> propertyAnnotations = Stream.of(annotations).filter(this::isAnnotationOfType).map(annotation -> (A) annotation).collect(toList());
    if (propertyAnnotations.size() > 1) {
      throw new IllegalStateException(format("More than one property annotation is present on declared method %s.", elementName));
    }
    if (propertyAnnotations.size() == 1) {
      return doTransform(propertyAnnotations.get(0), elementType, genericTypes);
    }
    return null;
  }

  protected boolean isMultiValue(Class<?> clazz) {
    Class<?> componentType = clazz.getComponentType();
    if (componentType != null) {
      // Class is an array
      return true;
    }
    // If class is a collection or map type then is multiValue
    return Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz);
  }

  protected abstract T doTransform(A annotation, Class<?> elementType, Class<?>... genericTypes);


  protected abstract boolean isAnnotationOfType(Annotation annotation);
}
