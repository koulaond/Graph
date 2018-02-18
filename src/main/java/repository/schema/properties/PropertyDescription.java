package repository.schema.properties;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
public abstract class PropertyDescription<T extends Serializable> {
    private String propertyName;

    private Class<T> propertyType;

    boolean mandatory;

    public PropertyDescription(String propertyName, Class<T> propertyType, boolean mandatory) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.mandatory = mandatory;
    }
}
