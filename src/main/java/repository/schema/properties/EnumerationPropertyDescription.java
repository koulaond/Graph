package repository.schema.properties;

import lombok.Getter;

@Getter
public class EnumerationPropertyDescription extends PropertyDescription<String> {

    private Class enumClass;

    public EnumerationPropertyDescription(String propertyName, Class<String> propertyType, boolean mandatory, boolean multiValue, Class enumClass) {
        super(propertyName, propertyType, mandatory, multiValue);
        this.enumClass = enumClass;
    }
}
