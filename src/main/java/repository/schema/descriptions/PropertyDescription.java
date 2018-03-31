package repository.schema.descriptions;

import lombok.Getter;
import java.io.Serializable;

@Getter
public abstract class PropertyDescription<T extends Serializable> {
    private String propertyName;

    private Class<T> propertyType;

    boolean mandatory;

    boolean multiValue;

    public PropertyDescription(String propertyName, Class<T> propertyType, boolean mandatory, boolean multiValue) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.mandatory = mandatory;
        this.multiValue = multiValue;
    }
}
