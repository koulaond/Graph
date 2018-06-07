package core.schema.descriptions;

import model.Direction;
import java.util.Set;
import java.util.UUID;

public class RelationshipDescription<RT> extends PropertyDescription<UUID> {
    private Class<RT> referencedClass;

    private Set<PropertyDescription> propertyDescriptions;

    private Direction direction;

    public RelationshipDescription(String propertyName,
                                   boolean mandatory,
                                   boolean multiValue,
                                   boolean immutable,
                                   Class<RT> referencedClass,
                                   Set<PropertyDescription> propertyDescriptions,
                                   Direction direction) {
        super(propertyName, UUID.class, mandatory, multiValue, immutable);
        this.referencedClass = referencedClass;
        this.propertyDescriptions = propertyDescriptions;
        this.direction = direction;
    }

    public Class<RT> getReferencedClass() {
        return referencedClass;
    }

    public Set<PropertyDescription> getPropertyDescriptions() {
        return propertyDescriptions;
    }

    public Direction getDirection() {
        return direction;
    }
}
