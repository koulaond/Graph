package core.schema.assemble.definitions.property;

import java.util.Date;

public class DatePropertyDefinition extends PropertyDefinition {

  private Date minDate;
  private Date maxDate;

  public DatePropertyDefinition(boolean mandatory, boolean multiValue, boolean immutable, Date minDate, Date maxDate) {
    super(mandatory, multiValue, immutable);
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
