package repository.schema.properties;

import lombok.Getter;

@Getter
public class StringPropertyDescription extends PropertyDescription<String> {

    private int minLength;

    private int maxLength;

    public StringPropertyDescription(String propertyName, Class<String> propertyType, boolean mandatory, int minLength, int maxLength) {
        super(propertyName, propertyType, mandatory);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public StringPropertyDescription(String propertyName, Class<String> propertyType, boolean mandatory) {
        this(propertyName, propertyType, mandatory, 0, Integer.MAX_VALUE);
    }
}
