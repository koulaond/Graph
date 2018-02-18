package repository.schema.properties;

import java.math.BigDecimal;

public class NumericPropertyDescription extends PropertyDescription<BigDecimal>{

    private BigDecimal minValue;

    private BigDecimal maxValue;

    public NumericPropertyDescription(String propertyName, Class<BigDecimal> propertyType, boolean mandatory, BigDecimal minValue, BigDecimal maxValue) {
        super(propertyName, propertyType, mandatory);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}
