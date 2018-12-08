package utils;

import java.util.Objects;

public class CommonUtils {

  private CommonUtils() {
    throw new IllegalStateException("No instance of class " + this.getClass().getName());
  }

  /**
   * Returns uncapitalized form of input value: InputValue -> inputValue
   */
  public static String uncapitalize(String value) {
    if (Objects.requireNonNull(value).isEmpty()) {
      return value;
    }
    if (value.length() == 1) {
      return value.toLowerCase();
    }
    return value.substring(0, 1).toLowerCase() + value.substring(1, value.length());
  }
}
