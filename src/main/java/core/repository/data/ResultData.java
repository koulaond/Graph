package core.repository.data;

import java.io.Serializable;

public class ResultData<T extends Serializable> {
    private String propertyName;
    private T value;

    public ResultData(String propertyName, T value) {
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
