package repository.schema.descriptions;

import lombok.Getter;
import repository.schema.Direction;

import java.util.Set;
import java.util.UUID;

@Getter
public class RelationshipDescription<RT> extends PropertyDescription<UUID> {
    private Class<RT> referencedClass;

    private Set<PropertyDescription> propertyDescriptions;

    private Direction direction;

    public RelationshipDescription(String propertyName,
                                   Class<UUID> propertyType,
                                   boolean mandatory,
                                   boolean multiValue,
                                   Class<RT> referencedClass,
                                   Set<PropertyDescription> propertyDescriptions,
                                   Direction direction) {
        super(propertyName, propertyType, mandatory, multiValue);
        this.referencedClass = referencedClass;
        this.propertyDescriptions = propertyDescriptions;
        this.direction = direction;
    }
}
