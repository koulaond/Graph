package repository.schema.descriptions;

import java.util.Date;

public class DatePropertyDescription extends PropertyDescription<Date> {

    private String dateFormat;

    public DatePropertyDescription(String propertyName,
                                   boolean mandatory,
                                   boolean multiValue,
                                   boolean immutable,
                                   String dateFormat) {
        super(propertyName, Date.class, mandatory, multiValue, immutable);
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
