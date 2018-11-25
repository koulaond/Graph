package core.repository.processing.query.conditions.text;

import core.repository.processing.query.conditions.Matcher;

class RegexMatcher implements Matcher<String> { private String value;

  public RegexMatcher(String value) {
    this.value = value;
  }

  @Override
  public boolean matches(String toCompare) {
    return toCompare.matches(value);
  }
}
