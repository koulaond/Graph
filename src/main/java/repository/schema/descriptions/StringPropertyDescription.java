package repository.schema.descriptions;

import lombok.Getter;

@Getter
public class StringPropertyDescription extends PropertyDescription<String> {

    private int minLength;

    private int maxLength;

    public StringPropertyDescription(String propertyName, Class<String> propertyType, boolean mandatory, boolean multiValue, int minLength, int maxLength) {
        super(propertyName, propertyType, mandatory, multiValue);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public StringPropertyDescription(String propertyName, Class<String> propertyType, boolean mandatory, boolean multiValue) {
        this(propertyName, propertyType, mandatory, multiValue, 0, Integer.MAX_VALUE);
    }
}
