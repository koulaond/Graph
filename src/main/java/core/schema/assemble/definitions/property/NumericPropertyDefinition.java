package core.schema.assemble.definitions.property;

import java.math.BigDecimal;

public class NumericPropertyDefinition extends PropertyDefinition {

  private BigDecimal minValue;
  private BigDecimal maxValue;

  public NumericPropertyDefinition(String propertyName) {
    this(propertyName, false, false, false);
  }

  public NumericPropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable) {
    this(propertyName, mandatory, multiValue, immutable, new BigDecimal(Long.MIN_VALUE), new BigDecimal(Long.MAX_VALUE));
  }

  public NumericPropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable, BigDecimal minValue, BigDecimal maxValue) {
    super(propertyName, mandatory, multiValue, immutable);
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  public BigDecimal getMinValue() {
    return minValue;
  }

  public BigDecimal getMaxValue() {
    return maxValue;
  }
}
