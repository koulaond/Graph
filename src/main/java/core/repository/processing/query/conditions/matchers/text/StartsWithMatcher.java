package core.repository.processing.query.conditions.matchers.text;

import core.repository.processing.query.conditions.matchers.Matcher;

class StartsWithMatcher implements Matcher<String> {
  private String value;

  public StartsWithMatcher(String value) {
    this.value = value;
  }

  @Override
  public boolean matches(String toCompare) {
    return toCompare.startsWith(value);
  }
}
