package repository.schema.descriptions;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class NumericPropertyDescription extends PropertyDescription<BigDecimal>{

    private BigDecimal minValue;

    private BigDecimal maxValue;

    public NumericPropertyDescription(String propertyName, Class<BigDecimal> propertyType, boolean mandatory, boolean multiValue, BigDecimal minValue, BigDecimal maxValue) {
        super(propertyName, propertyType, mandatory, multiValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public NumericPropertyDescription(String propertyName, Class<BigDecimal> propertyType, boolean mandatory, boolean multiValue) {
        this(propertyName, propertyType, mandatory, multiValue, new BigDecimal(0), new BigDecimal(Long.MAX_VALUE));
    }
}
