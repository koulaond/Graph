package repository.schema.introspection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static repository.schema.introspection.Constants.ERROR_DUPLICATE_ANNOTATIONS;

public class AnnotationIntrospector<AO extends AccessibleObject> {

    private Map<String, AO> fieldMap;

    public AnnotationIntrospector(Map<String, AO> objectsMap) {
        this.fieldMap = objectsMap;
    }

    /**
     * Process an array of accessible objects declared in @Node class and its superclasses (if inherits from someone)
     * and returns annotation for each accessible object according to the predicate. If there is more than one annotation
     * of the same type defined on one accessible object then {@link IllegalStateException} is thrown.
     *
     * @return map containing accessible object as a key and its particular annotation
     */
    public Map<AO, Annotation> introspectAnnotations(Predicate<Annotation> filterPredicate) {

        Map<AO, Annotation> propertyAnnotations = new HashMap<>();
        fieldMap.values().forEach(accessibleObject -> {
            List<Annotation> propertyAnnotationsForField = of(accessibleObject.getAnnotations())
                    .filter(filterPredicate)
                    .collect(toList());
            if (!propertyAnnotationsForField.isEmpty()) {
                if (propertyAnnotationsForField.size() > 1) {
                    throw new IllegalStateException(
                            format(
                                    ERROR_DUPLICATE_ANNOTATIONS,
                                    propertyAnnotationsForField.toString(),
                                    accessibleObject.toString()
                            )
                    );
                }
                propertyAnnotations.put(accessibleObject, propertyAnnotationsForField.get(0));
            }
        });
        return propertyAnnotations;
    }
}
