package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.annotations.properties.*;
import repository.schema.descriptions.PropertyDescription;
import repository.schema.descriptions.RelationshipDescription;
import repository.schema.descriptions.TypeDescription;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static repository.schema.annotations.properties.PropertyDeclaration.hasPropertyAnnotation;
import static repository.schema.introspection.Constants.*;

public class Introspector<T> {



    private Class<T> introspectedClass;

    private String typeName;

    private boolean immutable;

    private Set<PropertyDescription> propertyDescriptions;

    private Set<RelationshipDescription> relationshipDescriptions;

    public Introspector(Class<T> introspectedClass) {
        this.introspectedClass = introspectedClass;
    }

    public TypeDescription<T> introspect() {
        // Class must be a node
        if (introspectedClass.getAnnotation(Node.class) == null) {
            throw new IllegalArgumentException(format("Class %s is not annotated as @Node", introspectedClass.getName()));
        }
        Map<String, Field> fieldMap = new FieldCollector().collect(introspectedClass);
        Map<String, Method> getterMap = new GetterCollector().collect(introspectedClass);
        // This map contains property annotation for every field in the node class
        Map<Field, Annotation> propertyAnnotationsForFields = collectPropertyAnnotations(fieldMap);
        mergeFieldsAndGettersAnnotations(propertyAnnotationsForFields, getterMap);

        // TODO
        return null;
    }

    /**
     * Process an array of fields declared in @Node class and its superclasses (if inherits from someone)
     * and returns property annotation for each field.
     *
     * @param allFieldsMap class fields map containing fieldName:fieldObject
     * @return map containing field as a key and its property annotation
     */
    private Map<Field, Annotation> collectPropertyAnnotations(Map<String, Field> allFieldsMap) {
        Map<Field, Annotation> propertyAnnotations = new HashMap<>();
        allFieldsMap.values().forEach(field -> {
            List<Annotation> propertyAnnotationsForField = of(field.getAnnotations())
                    .filter(PropertyDeclaration::isPropertyAnnotation)
                    .collect(toList());
            if (!propertyAnnotationsForField.isEmpty()) {
                if (propertyAnnotationsForField.size() > 1) {
                    throw new IllegalStateException(
                            format(
                                    ERROR_DUPLICATE_PROPS_ON_FIELD,
                                    field.getDeclaringClass().getName(),
                                    field.getName(),
                                    propertyAnnotationsForField.toString()
                            )
                    );
                }
                propertyAnnotations.put(field, propertyAnnotationsForField.get(0));
            }
        });

        return propertyAnnotations;
    }

    /**
     * @param propertyAnnotations class fields map containing field:propertyAnnotation
     * @param gettersForFields    @param gettersForFields map with getters declared in the class, contains fieldName:getterMethod
     */
    private void mergeFieldsAndGettersAnnotations(Map<Field, Annotation> propertyAnnotations,
                                                  Map<String, Method> gettersForFields) {
        Set<Field> fieldKeys = propertyAnnotations.keySet();
        fieldKeys.forEach(field -> {
            Method getter = gettersForFields.get(field.getName());
            if (getter != null) {
                List<Annotation> propertyAnnotationsForGetter = of(getter.getAnnotations())
                        .filter(PropertyDeclaration::isPropertyAnnotation)
                        .collect(toList());
                if (!propertyAnnotationsForGetter.isEmpty()) {
                    if (propertyAnnotationsForGetter.size() > 1) {
                        throw new IllegalStateException(format(
                                ERROR_DUPLICATE_PROPS_ON_GETTER,
                                getter.getDeclaringClass().getName(),
                                getter.getName(),
                                propertyAnnotationsForGetter.toString()
                        ));
                    }
                    Annotation propertyAnnotation = propertyAnnotationsForGetter.get(0);
                    propertyAnnotations.put(field, propertyAnnotation);
                }

            }
        });
    }
}
