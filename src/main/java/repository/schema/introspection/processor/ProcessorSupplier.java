package repository.schema.introspection.processor;

import repository.schema.annotations.Relationship;
import repository.schema.annotations.properties.DateProperty;
import repository.schema.annotations.properties.EnumProperty;
import repository.schema.annotations.properties.NumericProperty;
import repository.schema.annotations.properties.StringProperty;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Static class for supplying @{@link PropertyDescriptionProcessor} instances.
 */
public class ProcessorSupplier {

    private static Map<Class<? extends Annotation>, PropertyDescriptionProcessor> processors = new HashMap<>();

    static {
        processors.put(DateProperty.class, new DatePropertyDescriptionProcessor());
        processors.put(EnumProperty.class, new EnumerationPropertyDescriptionProcessor());
        processors.put(NumericProperty.class, new NumericPropertyDescriptionProcessor());
        processors.put(StringProperty.class, new StringPropertyDescriptionProcessor());
        processors.put(Relationship.class, new RelationshipDescriptionProcessor());
    }

    public static PropertyDescriptionProcessor supply(Class<? extends Annotation> forType) {
        return processors.get(forType);
    }
}
