package core.repository.processing.query.conditions.text;

import core.repository.processing.query.conditions.Matcher;

class ContainsMatcher implements Matcher<String> {
  private String value;

  public ContainsMatcher(String value) {
    this.value = value;
  }

  @Override
  public boolean matches(String toCompare) {
    return toCompare.contains(value);
  }
}
