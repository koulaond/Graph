package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.annotations.properties.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static repository.schema.annotations.properties.PropertyDeclaration.hasPropertyAnnotation;

public class PropertyIntrospector {

    private static final String ERROR_DUPLICATE_PROPS_ON_FIELD = "Cannot process class %s. There is property annotations collision on field %s. Multiple property annotations are defined: %s";
    private static final String ERROR_DUPLICATE_PROPS_ON_GETTER = "Cannot process class %s. There is property annotations collision on getter method %s. Multiple property annotations are defined: %s";
    private static final String PREFIX_IS = "is";
    private static final String PREFIX_HAS = "has";
    private static final String PREFIX_GET = "get";

    public List<PropertyMetadata> introspect(Class declaringClass) {
        // Class must be a node
        if (declaringClass.getAnnotation(Node.class) == null) {
            throw new IllegalArgumentException(format("Class %s is not annotated as @Node", declaringClass.getName()));
        }
        Map<String, Field> allFieldsMap = new HashMap<>();
        Map<String, Method> gettersForFields = new HashMap<>();
        collectAllFieldsAndGetters(declaringClass, allFieldsMap, gettersForFields);
        // This map contains property annotation for every field in the node class
        Map<Field, Annotation> propertyAnnotationsForFields = collectPropertyAnnotations(allFieldsMap);
        mergeFieldsAndGettersAnnotations(propertyAnnotationsForFields, gettersForFields);

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

    private void collectAllFieldsAndGetters(Class declaringClass,
                                            Map<String, Field> allFieldsMap,
                                            Map<String, Method> allGettersMap) {
        of(declaringClass.getDeclaredFields()).forEach(field -> {
            String fieldName = field.getName();
            if (!allFieldsMap.containsKey(fieldName)) {
                allFieldsMap.put(fieldName, field);
            }
        });
        of(declaringClass.getDeclaredMethods()).forEach(method -> {
            String methodName = convertGetterNameToFieldName(method.getName());
            if (!allGettersMap.containsKey(methodName) && isGetter(method) && hasPropertyAnnotation(method)) {
                allGettersMap.put(methodName, method);
            }

        });
        Class superClass = declaringClass.getSuperclass();
        if (superClass != null && !Object.class.equals(superClass)) {
            collectAllFieldsAndGetters(superClass, allFieldsMap, allGettersMap);
        }
    }

    private String convertGetterNameToFieldName(String getterName) {
        String fieldName = null;
        if (getterName.startsWith(PREFIX_GET) || getterName.startsWith(PREFIX_HAS)) {
            fieldName = getterName.substring(3);
        } else if (getterName.startsWith(PREFIX_IS)) {
            fieldName = getterName.substring(2);
        }
        return uncapitalize(fieldName);
    }

    private boolean isGetter(Method method) {
        String name = method.getName();
        Class returnType = method.getReturnType();
        return (name.startsWith(PREFIX_GET) || name.startsWith(PREFIX_HAS) || name.startsWith(PREFIX_IS))
                && !returnType.equals(Void.class)
                && method.getParameterCount() == 0;
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String uncapitalize(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
