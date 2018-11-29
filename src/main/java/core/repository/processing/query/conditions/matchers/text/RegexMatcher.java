package core.repository.processing.query.conditions.matchers.text;

import core.repository.processing.query.conditions.matchers.Matcher;

class RegexMatcher implements Matcher<String> { private String value;

  public RegexMatcher(String value) {
    this.value = value;
  }

  @Override
  public boolean matches(String toCompare) {
    return toCompare.matches(value);
  }
}
