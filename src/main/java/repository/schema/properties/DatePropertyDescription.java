package repository.schema.properties;

import lombok.Getter;

import java.util.Date;

@Getter
public class DatePropertyDescription extends PropertyDescription<Date> {

    private String dateFormat;

    public DatePropertyDescription(String propertyName, Class<Date> propertyType, boolean mandatory, String dateFormat) {
        super(propertyName, propertyType, mandatory);
        this.dateFormat = dateFormat;
    }
}
