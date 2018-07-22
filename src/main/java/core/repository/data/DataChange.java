package core.repository.data;

import java.io.Serializable;

public class DataChange<T extends Serializable> {
    private String propertyName;
    private T newValue;

    public DataChange(String propertyName, T newValue) {
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
