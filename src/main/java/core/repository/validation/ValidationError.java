package core.repository.validation;

import java.io.Serializable;

public class ValidationError {
    private Object parentObject;
    private String propertyName;
    private Class<?> propertyClass;
    private String message;

    public ValidationError(Object parentObject, String propertyName, Class<?> propertyClass, String message) {
        this.parentObject = parentObject;
        this.propertyName = propertyName;
        this.propertyClass = propertyClass;
        this.message = message;
    }

    public Object getParentObject() {
        return parentObject;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class<?> getPropertyClass() {
        return propertyClass;
    }

    public String getMessage() {
        return message;
    }
}

