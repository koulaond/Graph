package core.repository.data;

import java.io.Serializable;

public class DataUnit<T extends Serializable> {
    private String propertyName;
    private T value;

    public DataUnit(String propertyName, T value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public T getValue() {
        return value;
    }
}
