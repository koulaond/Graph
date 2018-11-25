package core.repository.processing.query.conditions.date;

import java.util.Date;

import core.repository.processing.query.conditions.Matcher;

class BeforeDateMatcher implements Matcher<Date> {
  private Date to;

  public BeforeDateMatcher(Date to) {
    this.to = to;
  }

  @Override
  public boolean matches(Date value) {
    return value.before(to);
  }
}
