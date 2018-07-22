package core.schema.descriptions;

import java.io.Serializable;

public class Description<T extends Serializable> {
    private String name;
    private Class<T> describedClass;

    public Description(String propertyName, Class<T> propertyNameClass) {
        this.name = propertyName;
        this.describedClass = propertyNameClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<T> getDescribedClass() {
        return describedClass;
    }

    public void setDescribedClass(Class<T> describedClass) {
        this.describedClass = describedClass;
    }
}
