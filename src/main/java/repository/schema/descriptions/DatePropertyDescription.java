package repository.schema.descriptions;

import lombok.Getter;

import java.util.Date;

@Getter
public class DatePropertyDescription extends PropertyDescription<Date> {

    private String dateFormat;

    public DatePropertyDescription(String propertyName,  boolean mandatory, boolean multiValue, String dateFormat) {
        super(propertyName, Date.class, mandatory, multiValue);
        this.dateFormat = dateFormat;
    }
}
