package repository.schema.properties;

public class StringPropertyDescription extends PropertyDescription<String> {

    private int minLength = 0;

    private int maxLength = Integer.MAX_VALUE;

    public StringPropertyDescription(String propertyName, Class<String> propertyType, boolean mandatory, int minLength, int maxLength) {
        super(propertyName, propertyType, mandatory);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
}
