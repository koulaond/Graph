package core.repository.processing.query.conditions.matchers.date;

import java.util.Date;

import core.repository.processing.query.conditions.matchers.Matcher;

class BetweenDateMatcher implements Matcher<Date> {
  private Date from;
  private Date to;

  public BetweenDateMatcher(Date from, Date to) {
    if(from.after(to)) {
      throw new IllegalStateException("Date 'from' must be before date 'to'. ");
    }
    this.from = from;
    this.to = to;
  }

  @Override
  public boolean matches(Date value) {
    return value.after(from) && value.before(to);
  }
}
