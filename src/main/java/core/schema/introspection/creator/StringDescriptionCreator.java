package core.schema.introspection.creator;

import core.schema.annotations.properties.StringProperty;
import core.schema.descriptions.StringPropertyDescription;

/**
 * Creator class that processes @{@link StringProperty} annotation and returns {@link StringPropertyDescription} instance.
 */
public class StringDescriptionCreator
        implements DescriptionCreator<StringPropertyDescription, StringProperty> {

    @Override
    public StringPropertyDescription processProperty(StringProperty propertyAnnotation,
                                                     String fieldName,
                                                     boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        int minLength = propertyAnnotation.minLength();
        int maxLength = propertyAnnotation.maxLength();
        return new StringPropertyDescription(resolveName(name, fieldName), fieldName, nonNull, multiValue, immutable, minLength, maxLength);
    }
}
