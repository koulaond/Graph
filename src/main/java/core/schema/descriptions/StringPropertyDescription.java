package core.schema.descriptions;

public class StringPropertyDescription extends PropertyDescription<String> {

    private int minLength;

    private int maxLength;

    public StringPropertyDescription(String propertyName,
                                     String ownerFieldName,
                                     boolean mandatory,
                                     boolean multiValue,
                                     boolean immutable,
                                     int minLength,
                                     int maxLength) {
        super(propertyName, String.class, ownerFieldName, mandatory, multiValue, immutable);
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public StringPropertyDescription(String propertyName,
                                     String ownerFieldName,
                                     boolean mandatory,
                                     boolean multiValue,
                                     boolean immutable) {
        this(propertyName, ownerFieldName, mandatory, multiValue, immutable, 0, Integer.MAX_VALUE);
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
