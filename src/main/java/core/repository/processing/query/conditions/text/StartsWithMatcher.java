package core.repository.processing.query.conditions.text;

import core.repository.processing.query.conditions.Matcher;

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
