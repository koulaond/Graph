package core.repository.processing.query.conditions.text;

import core.repository.processing.query.conditions.Matcher;

public class TextMatcherFactory {
  public Matcher<String> contains(String value) {
    return new ContainsMatcher(value);
  }

  public Matcher<String> endsWith(String value) {
    return new EndsWithMatcher(value);
  }

  public Matcher<String> startsWith(String value) {
    return new StartsWithMatcher(value);
  }

  public Matcher<String> exact(String value) {
    return new ExactMatcher(value);
  }

  public Matcher<String> regex(String value) {
    return new RegexMatcher(value);
  }
}
