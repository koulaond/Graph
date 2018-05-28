package repository.schema.descriptions;

import java.io.Serializable;

public abstract class PropertyDescription<T extends Serializable> {
    private String type;

    private Class<T> typeClass;

    boolean mandatory;

    boolean multiValue;

    boolean immutable;

    public PropertyDescription(String type,
                               Class<T> typeClass,
                               boolean mandatory,
                               boolean multiValue,
                               boolean immutable) {
        this.type = type;
        this.typeClass = typeClass;
        this.mandatory = mandatory;
        this.multiValue = multiValue;
        this.immutable = immutable;
    }

    public String getType() {
        return type;
    }

    public Class<T> getTypeClass() {
        return typeClass;
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
