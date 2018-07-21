package core.schema.introspection.creator;

import core.schema.annotations.Relationship;
import core.schema.annotations.properties.DateProperty;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Static class for supplying @{@link DescriptionCreator} instances.
 */
public class CreatorSupplier {

    private static Map<Class<? extends Annotation>, DescriptionCreator> processors = new HashMap<>();

    static {
        processors.put(DateProperty.class, new DateDescriptionCreator());
        processors.put(EnumProperty.class, new EnumerationDescriptionCreator());
        processors.put(NumericProperty.class, new NumericDescriptionCreator());
        processors.put(StringProperty.class, new StringDescriptionCreator());
        processors.put(Relationship.class, new RelationshipDescriptionCreator());
    }

    public static DescriptionCreator supply(Class<? extends Annotation> forType) {
        return processors.get(forType);
    }
}
