package core.repository.processing.query.conditions.matchers.number;

import java.math.BigDecimal;

import core.repository.processing.query.conditions.matchers.Matcher;

class BetweenNumbersMatcher implements Matcher<BigDecimal> {

  private BigDecimal valueFrom;
  private BigDecimal valueTo;

  public BetweenNumbersMatcher(BigDecimal valueFrom, BigDecimal valueTo) {
    this.valueFrom = valueFrom;
    this.valueTo = valueTo;
  }

  @Override
  public boolean matches(BigDecimal toCompare) {
    return toCompare.compareTo(valueFrom) == 1 && toCompare.compareTo(valueTo) == -1;
  }
}
