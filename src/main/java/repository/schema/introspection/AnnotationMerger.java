package repository.schema.introspection;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static repository.schema.introspection.Utils.convertGetterNameToFieldName;

/**
 * Helper class for merging Property/Relation annotations on field and getters to single map.
 * The merger does following for each getter:annotation value in the second map:
 * 1. Converts getter name to particular field name
 * 2. Finds {@link Field} in the first map
 * 3. Replaces its annotation by the annotation assigned to the getter
 * @param <AT> accessible object type
 */
public class AnnotationMerger<AT extends Annotation> {

    /**
     * @param annForFields all annotations defined on fields
     * @param annForGetters all annotations defined on getters
     * @return merged map with field:annotation values
     */
    public Map<Field, AT> merge(Map<Field, AT> annForFields, Map<Method, AT> annForGetters){
        Map<Field, AT> mergedAnnotations = new HashMap<>();
        annForFields.forEach((field, annotation) -> mergedAnnotations.put(field, annotation));
        annForGetters.forEach((getter, annotation) -> {
            String fieldName = convertGetterNameToFieldName(getter.getName());
            Field field = annForFields.keySet()
                    .stream()
                    .filter(fld -> fld.getName().equals(fieldName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(String.format("Field %s not found.", fieldName)));
            mergedAnnotations.put(field, annotation);
        });
        return mergedAnnotations;
    }

}
