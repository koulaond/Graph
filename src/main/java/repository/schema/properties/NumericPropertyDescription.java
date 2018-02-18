package repository.schema.properties;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class NumericPropertyDescription extends PropertyDescription<BigDecimal>{

    private BigDecimal minValue;

    private BigDecimal maxValue;

    public NumericPropertyDescription(String propertyName, Class<BigDecimal> propertyType, boolean mandatory, BigDecimal minValue, BigDecimal maxValue) {
        super(propertyName, propertyType, mandatory);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public NumericPropertyDescription(String propertyName, Class<BigDecimal> propertyType, boolean mandatory) {
        this(propertyName, propertyType, mandatory, new BigDecimal(0), new BigDecimal(Long.MAX_VALUE));
    }
}
