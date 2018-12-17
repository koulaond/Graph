package core.schema.assembly.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import core.schema.annotations.properties.DateProperty;
import core.schema.assembly.definitions.property.DatePropertyDefinition;

public class DatePropertyPropertyAnnotationTransformer implements PropertyAnnotationTransformer<DateProperty> {

  @Override
  public DatePropertyDefinition transformToDefinition(DateProperty annotation, boolean multiValue) {
    // TODO move to parametrization
    String dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
    SimpleDateFormat format = new SimpleDateFormat(dateFormat);
    Date minDate;
    Date maxDate;
    try {
      minDate = format.parse(annotation.minDate());
      maxDate = format.parse(annotation.maxDate());
    } catch (ParseException e) {
      throw new IllegalArgumentException("Invalid date value.", e);
    }
    return new DatePropertyDefinition(annotation.name(), annotation.nonNull(), multiValue, annotation.immutable(), minDate, maxDate);
  }
}
