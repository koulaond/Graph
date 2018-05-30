package core.schema.descriptions;

import java.math.BigDecimal;

public class NumericPropertyDescription extends PropertyDescription<BigDecimal>{

    private BigDecimal minValue;

    private BigDecimal maxValue;

    public NumericPropertyDescription(String propertyName,
                                      boolean mandatory,
                                      boolean multiValue,
                                      boolean immutable,
                                      BigDecimal minValue,
                                      BigDecimal maxValue) {
        super(propertyName, BigDecimal.class, mandatory, multiValue, immutable);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public NumericPropertyDescription(String propertyName,
                                      boolean mandatory,
                                      boolean multiValue,
                                      boolean immutable) {
        this(propertyName, mandatory, multiValue, immutable, new BigDecimal(0), new BigDecimal(Long.MAX_VALUE));
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }
}
