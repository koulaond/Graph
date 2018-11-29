package core.repository.processing.query.conditions.matchers.number;

import java.math.BigDecimal;

import core.repository.processing.query.conditions.matchers.Matcher;

class GreaterThanMatcher implements Matcher<BigDecimal> {

  private BigDecimal value;

  public GreaterThanMatcher(BigDecimal value) {
    this.value = value;
  }

  @Override
  public boolean matches(BigDecimal toCompare) {
    return toCompare.compareTo(value) == 1;
  }
}
