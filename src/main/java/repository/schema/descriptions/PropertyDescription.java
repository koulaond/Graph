package repository.schema.descriptions;

import java.io.Serializable;

public abstract class PropertyDescription<T extends Serializable> {
    private String propertyName;

    private Class<T> propertyType;

    boolean mandatory;

    boolean multiValue;

    boolean immutable;

    public PropertyDescription(String propertyName,
                               Class<T> propertyType,
                               boolean mandatory,
                               boolean multiValue,
                               boolean immutable) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.mandatory = mandatory;
        this.multiValue = multiValue;
        this.immutable = immutable;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class<T> getPropertyType() {
        return propertyType;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public boolean isMultiValue() {
        return multiValue;
    }

    public boolean isImmutable() {
        return immutable;
    }
}
