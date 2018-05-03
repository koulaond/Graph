package repository.schema.introspection;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static repository.schema.introspection.Utils.convertGetterNameToFieldName;

public class AnnotationMerger {

    public Map<String, Annotation> merge(Map<Field, Annotation> annForFields, Map<Method, Annotation> annForGetters){
        Map<String, Annotation> mergedAnnotations = new HashMap<>();
        annForFields.forEach((field, annotation) -> mergedAnnotations.put(field.getName(), annotation));
        annForGetters.forEach((getter, annotation) -> mergedAnnotations.put(convertGetterNameToFieldName(getter.getName()), annotation));
        return mergedAnnotations;
    }
}
