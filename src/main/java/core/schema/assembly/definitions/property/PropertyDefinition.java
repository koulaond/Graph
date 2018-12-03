package core.schema.assembly.definitions.property;

import static java.util.Objects.requireNonNull;

public abstract class PropertyDefinition {

  protected String propertyName;
  protected boolean mandatory;
  protected boolean multiValue;
  protected boolean immutable;

  public PropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable) {
    this.propertyName = requireNonNull(propertyName);
    this.mandatory = requireNonNull(mandatory);
    this.multiValue = requireNonNull(multiValue);
    this.immutable = requireNonNull(immutable);
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
