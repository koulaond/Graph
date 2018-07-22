package core.schema.descriptions;

public class EnumerationPropertyDescription extends PropertyDescription<String> {

    private Class enumClass;

    public EnumerationPropertyDescription(String propertyName,
                                          String ownerFieldName,
                                          boolean mandatory,
                                          boolean multiValue,
                                          boolean immutable,
                                          Class enumClass) {
        super(propertyName, String.class, ownerFieldName, mandatory, multiValue, immutable);
        this.enumClass = enumClass;
    }

    public Class getEnumClass() {
        return enumClass;
    }
}
