package core.schema.descriptions;

import java.io.Serializable;

public class Description<T extends Serializable> {
    private String propertyName;
    private Class<T> propertyNameClass;

    public Description(String propertyName, Class<T> propertyNameClass) {
        this.propertyName = propertyName;
        this.propertyNameClass = propertyNameClass;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Class<T> getPropertyNameClass() {
        return propertyNameClass;
    }

    public void setPropertyNameClass(Class<T> propertyNameClass) {
        this.propertyNameClass = propertyNameClass;
    }
}
