package repository.schema.descriptions;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class NumericPropertyDescription extends PropertyDescription<BigDecimal>{

    private BigDecimal minValue;

    private BigDecimal maxValue;

    public NumericPropertyDescription(String propertyName, boolean mandatory, boolean multiValue, BigDecimal minValue, BigDecimal maxValue) {
        super(propertyName, BigDecimal.class, mandatory, multiValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public NumericPropertyDescription(String propertyName, boolean mandatory, boolean multiValue) {
        this(propertyName, mandatory, multiValue, new BigDecimal(0), new BigDecimal(Long.MAX_VALUE));
    }
}
