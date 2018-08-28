package core.repository.data;

import java.io.Serializable;

public class DataUnit<T extends Serializable> {
    private String propertyName;
    private T newValue;

    public DataUnit(String propertyName, T newValue) {
        this.propertyName = propertyName;
        this.newValue = newValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public T getNewValue() {
        return newValue;
    }
}
