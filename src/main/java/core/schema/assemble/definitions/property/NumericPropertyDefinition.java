package core.schema.assemble.definitions.property;

import java.math.BigDecimal;

public class NumericPropertyDefinition extends PropertyDefinition {

  private BigDecimal minValue;
  private BigDecimal maxValue;

  public NumericPropertyDefinition(boolean mandatory, boolean multiValue, boolean immutable, BigDecimal minValue, BigDecimal maxValue) {
    super(mandatory, multiValue, immutable);
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
