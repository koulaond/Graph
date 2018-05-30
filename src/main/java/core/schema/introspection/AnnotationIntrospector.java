package core.schema.introspection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.*;
import java.util.function.Predicate;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static core.schema.introspection.Constants.ERROR_DUPLICATE_ANNOTATIONS;

/**
 * Introspector class that processes an input map containing {@link AccessibleObject} instances. For each instance
 * tries to find its declared annotation according to the predicate. If the annotation exists, it is stored to result
 * map where {@link AccessibleObject} is the key and the annotation is value.
 * @param <AO> accessible object
 */
public class AnnotationIntrospector<AO extends AccessibleObject> {

    private Collection<AO> accessibleObjects;

    public AnnotationIntrospector(Collection<AO> accessibleObjects) {
        this.accessibleObjects = accessibleObjects;
    }

    /**
     * Process an array of accessible objects declared in @Node class and its superclasses (if inherits from someone)
     * and returns annotation for each {@link AccessibleObject} according to the predicate. If there is more than one
     * annotation matching the predicate defined on the same {@link AccessibleObject}
     * then {@link IllegalStateException} is thrown.
     *
     * @return map containing accessible object as a key and its particular annotation
     */
    public Map<AO, Annotation> introspectAnnotations(Predicate<Annotation> filterPredicate) {
        Map<AO, Annotation> propertyAnnotations = new HashMap<>();
        accessibleObjects.forEach(accessibleObject -> {
            List<Annotation> propertyAnnotationsForField = of(accessibleObject.getDeclaredAnnotations())
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
