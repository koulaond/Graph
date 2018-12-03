package core.schema.assembly.definitions.property;

import java.util.Date;

import static java.util.Objects.requireNonNull;

public class DatePropertyDefinition extends PropertyDefinition {

  private Date minDate;
  private Date maxDate;

  public DatePropertyDefinition(String propertyName) {
    this(propertyName, false, false, false);
  }

  public DatePropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable) {
    this(propertyName, mandatory, multiValue, immutable, new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));

  }

  public DatePropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable, Date minDate, Date maxDate) {
    super(propertyName, mandatory, multiValue, immutable);
    this.minDate = requireNonNull(minDate);
    this.maxDate = requireNonNull(maxDate);
  }

  public Date getMinDate() {
    return minDate;
  }

  public Date getMaxDate() {
    return maxDate;
  }
}
