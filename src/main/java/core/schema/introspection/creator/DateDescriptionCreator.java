package core.schema.introspection.creator;

import core.schema.annotations.properties.DateProperty;
import core.schema.descriptions.DatePropertyDescription;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creator class that processes @{@link DateProperty} annotation and returns {@link DatePropertyDescription} instance.
 */
public class DateDescriptionCreator implements DescriptionCreator<DatePropertyDescription, DateProperty> {

    @Override
    public DatePropertyDescription processProperty(DateProperty propertyAnnotation,
                                                   String fieldName,
                                                   boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        String dateFormat = propertyAnnotation.dateFormat();
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        String maxDate = propertyAnnotation.maxDate();
        String minDate = propertyAnnotation.minDate();

        Date maxDateRes;
        try {
            maxDateRes = maxDate == null ? null : formatter.parse(maxDate);
        } catch (ParseException e) {
            maxDateRes = null;
        }

        Date minDateRes;
        try {
            minDateRes = minDate == null ? null : formatter.parse(minDate);
        } catch (ParseException e) {
            minDateRes = null;
        }
        return new DatePropertyDescription(resolveName(name, fieldName), fieldName, nonNull, multiValue, immutable, minDateRes, maxDateRes);
    }
}
