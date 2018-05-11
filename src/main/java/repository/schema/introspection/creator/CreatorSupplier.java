package repository.schema.introspection.creator;

import repository.schema.annotations.Relationship;
import repository.schema.annotations.properties.DateProperty;
import repository.schema.annotations.properties.EnumProperty;
import repository.schema.annotations.properties.NumericProperty;
import repository.schema.annotations.properties.StringProperty;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Static class for supplying @{@link PropertyDescriptionCreator} instances.
 */
public class CreatorSupplier {

    private static Map<Class<? extends Annotation>, PropertyDescriptionCreator> processors = new HashMap<>();

    static {
        processors.put(DateProperty.class, new DatePropertyDescriptionCreator());
        processors.put(EnumProperty.class, new EnumerationPropertyDescriptionCreator());
        processors.put(NumericProperty.class, new NumericPropertyDescriptionCreator());
        processors.put(StringProperty.class, new StringPropertyDescriptionCreator());
        processors.put(Relationship.class, new RelationshipDescriptionCreator());
    }

    public static PropertyDescriptionCreator supply(Class<? extends Annotation> forType) {
        return processors.get(forType);
    }
}
