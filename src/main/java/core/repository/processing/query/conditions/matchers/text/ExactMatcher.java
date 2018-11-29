package core.repository.processing.query.conditions.matchers.text;

import core.repository.processing.query.conditions.matchers.Matcher;

class ExactMatcher implements Matcher<String> {
  private String value;

  public ExactMatcher(String value) {
    this.value = value;
  }

  @Override
  public boolean matches(String toCompare) {
    return toCompare.equals(value);
  }
}
