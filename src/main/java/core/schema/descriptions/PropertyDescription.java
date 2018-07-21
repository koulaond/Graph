package core.schema.descriptions;

import java.io.Serializable;

public abstract class PropertyDescription<T extends Serializable> extends Description<T> {
    private boolean mandatory;
    private boolean multiValue;
    private boolean immutable;

    public PropertyDescription(String propertyName,
                               Class<T> propertyNameClass,
                               boolean mandatory,
                               boolean multiValue,
                               boolean immutable) {
        super(propertyName, propertyNameClass);
        this.mandatory = mandatory;
        this.multiValue = multiValue;
        this.immutable = immutable;
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
