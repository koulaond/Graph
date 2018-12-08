package core.schema.assembly.definitions.property;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PropertyDefinition that = (PropertyDefinition) o;
    // Property uniqueness is given by its name
    return new EqualsBuilder()
        .append(propertyName, that.propertyName)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(propertyName)
        .toHashCode();
  }
}
