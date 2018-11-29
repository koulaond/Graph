package core.repository.processing.query.conditions.matchers.number;

import java.math.BigDecimal;

import core.repository.processing.query.conditions.matchers.Matcher;

class EqualsMatcher implements Matcher<BigDecimal> {

  private BigDecimal value;

  public EqualsMatcher(BigDecimal value) {
    this.value = value;
  }

  @Override
  public boolean matches(BigDecimal toCompare) {
    return toCompare.compareTo(value) == 0;
  }
}
