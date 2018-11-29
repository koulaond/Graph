package core.repository.processing.query.conditions.matchers.text;

import core.repository.processing.query.conditions.matchers.Matcher;

class EndsWithMatcher implements Matcher<String> {
  private String value;

  public EndsWithMatcher(String value) {
    this.value = value;
  }

  @Override
  public boolean matches(String toCompare) {
    return toCompare.endsWith(value);
  }
}
