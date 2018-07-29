package core.repository.validation;

import java.io.Serializable;

public class ValidationError {
    private Serializable parentObject;
    private String propertyName;
    private Class<?> propertyClass;
    private String message;

    public ValidationError(Serializable parentObject, String propertyName, Class<?> propertyClass, String message) {
        this.parentObject = parentObject;
        this.propertyName = propertyName;
        this.propertyClass = propertyClass;
        this.message = message;
    }

    public Serializable getParentObject() {
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

