package core.repository.processing.query.conditions.number;

import java.math.BigDecimal;

import core.repository.processing.query.conditions.Matcher;

public class NumberMatcherFactory {

  public Matcher<BigDecimal> isEqualTo(BigDecimal value) {
    return new EqualsMatcher(value);
  }

  public Matcher<BigDecimal> lessThan(BigDecimal value) {
    return new LessThanMatcher(value);
  }

  public Matcher<BigDecimal> greatedThan(BigDecimal value) {
    return new GreaterThanMatcher(value);
  }

  public Matcher<BigDecimal> betweenNumbers(BigDecimal valueFrom, BigDecimal valueTo) {
    return new BetweenNumbersMatcher(valueFrom, valueTo);
  }
}
