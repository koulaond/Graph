package core.repository.processing.query.conditions.matchers.date;

import java.util.Date;

import core.repository.processing.query.conditions.matchers.Matcher;

class AfterDateMatcher implements Matcher<Date> {
  private Date from;

  public AfterDateMatcher(Date from) {
    this.from = from;
  }

  @Override
  public boolean matches(Date value) {
    return value.after(from);
  }
}
