package core.repository.processing.command.result;

import java.io.Serializable;

public class ResultDataChange<S extends Serializable> {

  private String propertyName;
  private S oldValue;
  private S newValue;

  public ResultDataChange(String propertyName, S oldValue, S newValue) {
    this.propertyName = propertyName;
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

  public String getPropertyName() {
    return propertyName;
  }

  public S getOldValue() {
    return oldValue;
  }

  public S getNewValue() {
    return newValue;
  }
}
