package repository.schema.introspection;

import com.google.common.base.Strings;
import com.sun.deploy.util.StringUtils;
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

    public List<PropertyMetadata> introspect(Class clazz) {
        // Class must be a node
        if (clazz.getAnnotation(Node.class) == null) {
            throw new IllegalArgumentException(format("Class %s is not annotated as @Node", clazz.getName()));
        }
        Field[] fields = clazz.getFields();
        // This map contains property annotation for every field in the node class
        Map<Field, Annotation> fieldsAnnotations = processFields(fields);

        // TODO
        return null;
    }

    /**
     * Process an array of fields declared in @Node class and returns property annotation for each field.
     *
     * @param fields fields array
     * @return map containing field as a key and its property annotation
     */
    private Map<Field, Annotation> processFields(Field[] fields) {
        Map<Field, Annotation> fieldsAnnotations = new HashMap<>();
        Stream.of(fields).forEach(field -> {
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

    private Map<Field, Method> findGettersForFields(Field[] fields, Class declaringClass) {

    }

    private Method findGetterForField(Field field, Class declaringClass) {
        String fieldName = capitalize(field.getName());
        Class<?> fieldType = field.getType();

        if (Boolean.class.equals(fieldType) || boolean.class.equals(fieldType)) {
            Method getter = findGetter(PREFIX_IS + fieldName, fieldType, declaringClass);
            if(getter == null){
                findGetter(PREFIX_HAS + fieldName, fieldType, declaringClass);
            }
            return getter;
        } else {
            return findGetter(PREFIX_GET + fieldName, fieldType, declaringClass);
        }
    }

    private Method findGetter(String getterName, Class returnType, Class declaringClass){
        try {
            Method getter = declaringClass.getMethod(getterName);
            return getter != null && !getter.getReturnType().equals(returnType) ? null : getter;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
