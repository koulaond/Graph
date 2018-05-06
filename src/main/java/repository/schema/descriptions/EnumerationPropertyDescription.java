package repository.schema.descriptions;

import lombok.Getter;

@Getter
public class EnumerationPropertyDescription extends PropertyDescription<String> {

    private Class enumClass;

    public EnumerationPropertyDescription(String propertyName, boolean mandatory, boolean multiValue, Class enumClass) {
        super(propertyName, String.class, mandatory, multiValue);
        this.enumClass = enumClass;
    }
}
