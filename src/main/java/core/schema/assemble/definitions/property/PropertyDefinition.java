package core.schema.assemble.definitions.property;

public abstract class PropertyDefinition {

  protected String propertyName;
  protected boolean mandatory;
  protected boolean multiValue;
  protected boolean immutable;

  public PropertyDefinition(boolean mandatory, boolean multiValue, boolean immutable) {
    this.mandatory = mandatory;
    this.multiValue = multiValue;
    this.immutable = immutable;
  }

  public String getPropertyName() {
    return propertyName;
  }

  public boolean isMandatory() {
    return mandatory;
  }

  public boolean isMultiValue() {
    return multiValue;
  }

  public boolean isImmutable() {
    return immutable;
  }
}
