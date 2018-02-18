package repository.schema.properties;

import lombok.Getter;

@Getter
public class EnumerationPropertyDescription extends PropertyDescription<String> {

    private Class enumClass;

    public EnumerationPropertyDescription(String propertyName, Class<String> propertyType, boolean mandatory, Class enumClass) {
        super(propertyName, propertyType, mandatory);
        this.enumClass = enumClass;
    }
}
