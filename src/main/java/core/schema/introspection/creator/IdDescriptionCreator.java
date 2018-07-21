package core.schema.introspection.creator;

import core.schema.annotations.properties.IdProperty;
import core.schema.descriptions.IdDescription;

/**
 * Creator class that processes @{@link IdProperty} annotation and returns {@link IdDescription} instance.
 */
public class IdDescriptionCreator implements DescriptionCreator<IdDescription, IdProperty> {

    @Override
    public IdDescription processProperty(IdProperty annotation, String fieldName, boolean multiValue) {
        String name = resolveName(annotation.name(), fieldName);
        return new IdDescription(name);
    }
}
