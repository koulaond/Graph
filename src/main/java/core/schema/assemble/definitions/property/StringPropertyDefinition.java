package core.schema.assemble.definitions.property;

import static java.util.Objects.requireNonNull;

public class StringPropertyDefinition extends PropertyDefinition {

  private int minLength;
  private int maxLength;

  public StringPropertyDefinition(String propertyName) {
    this(propertyName, false, false, false);
  }

  public StringPropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable) {
    this(propertyName, mandatory, multiValue, immutable, 0, 65535);
  }

  public StringPropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable, int minLength, int maxLength) {
    super(propertyName, mandatory, multiValue, immutable);
    this.minLength = requireNonNull(minLength);
    this.maxLength = requireNonNull(maxLength);
  }

  public int getMinLength() {
    return minLength;
  }

  public int getMaxLength() {
    return maxLength;
  }
}
