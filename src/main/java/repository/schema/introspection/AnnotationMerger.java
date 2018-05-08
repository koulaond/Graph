package repository.schema.introspection;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static repository.schema.introspection.Utils.convertGetterNameToFieldName;

public class AnnotationMerger<AT extends Annotation> {

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
