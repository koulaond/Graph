package core.schema.descriptions;

import java.util.Date;

public class DatePropertyDescription extends PropertyDescription<Date> {

    private Date minDate;

    private Date maxDate;

    public DatePropertyDescription(String propertyName,
                                   String ownerFieldName,
                                   boolean mandatory,
                                   boolean multiValue,
                                   boolean immutable,
                                   Date minDate,
                                   Date maxDate) {
        super(propertyName, Date.class, ownerFieldName, mandatory, multiValue, immutable);
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public Date getMinDate() {
        return minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }
}
