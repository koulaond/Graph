package repository.schema.properties;

import java.util.Date;

public class DatePropertyDescription extends PropertyDescription<Date> {

    private String dateFormat;

    public DatePropertyDescription(String propertyName, Class<Date> propertyType, boolean mandatory, String dateFormat) {
        super(propertyName, propertyType, mandatory);
        this.dateFormat = dateFormat;
    }
}
