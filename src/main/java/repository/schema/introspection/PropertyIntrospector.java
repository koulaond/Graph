package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.annotations.properties.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class PropertyIntrospector {

    private static final String ERROR_DUPLICATE_PROPS = "Cannot process class %s. There is property annotations collision on field %s. Multiple property annotations are defined: %s";
    private static final String PREFIX_IS = "is";
    private static final String PREFIX_HAS = "has";
    private static final String PREFIX_GET = "get";

    public List<PropertyMetadata> introspect(Class declaringClass) {
        // Class must be a node
        if (declaringClass.getAnnotation(Node.class) == null) {
            throw new IllegalArgumentException(format("Class %s is not annotated as @Node", declaringClass.getName()));
        }
        Map<String, Field> allFieldsMap = new HashMap<>();
        Map<String, Method> allGettersMap = new HashMap<>();
        collectAllFieldsAndGetters(declaringClass, allFieldsMap, allGettersMap);
        // This map contains property annotation for every field in the node class
        Map<Field, Annotation> fieldsAnnotations = collectPropertyAnnotations(allFieldsMap, allGettersMap);

        // TODO
        return null;
    }

    /**
     * Process an array of fields declared in @Node class and its superclasses (if inherits from someone)
     * and returns property annotation for each field.
     *
     * @param allFieldsMap class fields map containing fieldName:fieldObject
     * @param allGettersMap map with getters declared in the class, contains getterName:getterObject
     * @return map containing field as a key and its property annotation
     */
    private Map<Field, Annotation> collectPropertyAnnotations(Map<String, Field> allFieldsMap, Map<String, Method> allGettersMap) {
        // TODO merge getter and field property annotations
        Map<Field, Annotation> fieldsAnnotations = new HashMap<>();
        allFieldsMap.values().forEach(field -> {
            List<Annotation> propertyAnnotations = Stream.of(field.getAnnotations())
                    .filter(PropertyDeclaration::isPropertyAnnotation)
                    .collect(Collectors.toList());
            if (!propertyAnnotations.isEmpty()) {
                if (propertyAnnotations.size() > 1) {
                    throw new IllegalStateException(
                            format(
                                    ERROR_DUPLICATE_PROPS,
                                    field.getDeclaringClass().getName(),
                                    field.getName(),
                                    propertyAnnotations.toString()
                            )
                    );
                }
                fieldsAnnotations.put(field, propertyAnnotations.get(0));
            }
        });
        return fieldsAnnotations;
    }

    private void collectAllFieldsAndGetters(Class declaringClass,
                                            Map<String, Field> allFieldsMap,
                                            Map<String, Method> allGettersMap) {
        Stream.of(declaringClass.getDeclaredFields()).forEach(field -> {
            String fieldName = field.getName();
            if (!allFieldsMap.containsKey(fieldName)) {
                allFieldsMap.put(fieldName, field);
            }
        });
        Stream.of(declaringClass.getDeclaredMethods()).forEach(method -> {
            String methodName = method.getName();
            if (!allGettersMap.containsKey(methodName) && isGetter(method)) {
                allGettersMap.put(methodName, method);
            }

        });
        Class superClass = declaringClass.getSuperclass();
        if (superClass != null) {
            collectAllFieldsAndGetters(superClass, allFieldsMap, allGettersMap);
        }
    }

    private boolean isGetter(Method method) {
        String name = method.getName();
        Class returnType = method.getReturnType();
        return (name.startsWith(PREFIX_GET) || name.startsWith(PREFIX_HAS) || name.startsWith(PREFIX_IS))
                && !returnType.equals(Void.class)
                && method.getParameterCount() == 0;
    }

    // TODO use this method to find getters for declared fields if exist
    private Method findGetterForField(Field field, Map<String, Method> allGetters) {
        String fieldName = capitalize(field.getName());
        Class<?> fieldType = field.getType();

        if (Boolean.class.equals(fieldType) || boolean.class.equals(fieldType)) {
            Method getter = allGetters.get(PREFIX_IS + fieldName);
            if (getter == null) {
                getter = allGetters.get(PREFIX_HAS + fieldName);
            }
            return getter;
        } else {
            return allGetters.get(PREFIX_GET + fieldName);
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
