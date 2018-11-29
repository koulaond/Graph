package core.repository.processing.query.conditions.matchers.number;

import java.math.BigDecimal;

import core.repository.processing.query.conditions.matchers.Matcher;

public class AtLeastOneEqualsMatcher implements Matcher<BigDecimal> {
  private BigDecimal[] values;

  public AtLeastOneEqualsMatcher(BigDecimal... values) {
    this.values = values;
  }

  @Override
  public boolean matches(BigDecimal toCompare) {
    if (values != null && values.length > 0) {
      for (BigDecimal value : values) {
        if (value.equals(toCompare)) return true;
      }
    }
    return false;
  }
}
