package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.annotations.properties.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

public class PropertyIntrospector {

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
                                    "There is property annotations collision on field %s. Multiple property annotations are defined: %s",
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
}
