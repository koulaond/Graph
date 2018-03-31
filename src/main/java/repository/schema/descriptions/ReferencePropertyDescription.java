package repository.schema.descriptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReferencePropertyDescription<RT> extends PropertyDescription<UUID> {
    private Class<RT> referencedClass;

    public ReferencePropertyDescription(String propertyName, Class<UUID> propertyType, boolean mandatory, boolean multiValue, Class<RT> referencedClass) {
        super(propertyName, propertyType, mandatory, multiValue);
        this.referencedClass = referencedClass;
    }
}
