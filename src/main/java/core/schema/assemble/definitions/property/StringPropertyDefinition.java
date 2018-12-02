package core.schema.assemble.definitions.property;

public class StringPropertyDefinition extends PropertyDefinition {

  private int minLength;
  private int maxLength;

  public StringPropertyDefinition(boolean mandatory, boolean multiValue, boolean immutable, int minLength, int maxLength) {
    super(mandatory, multiValue, immutable);
    this.minLength = minLength;
    this.maxLength = maxLength;
  }

  public int getMinLength() {
    return minLength;
  }

  public int getMaxLength() {
    return maxLength;
  }
}
