package core.repository.processing.query.conditions.matchers.date;

import java.util.Date;

import core.repository.processing.query.conditions.matchers.Matcher;

class OutsideDateMatcher implements Matcher<Date> {
  private Date to;
  private Date from;

  public OutsideDateMatcher(Date to, Date from) {
    if(from.before(to)) {
      throw new IllegalStateException("Date 'from' must be after date 'to' because matcher is for outside interval. ");
    }
    this.to = to;
    this.from = from;
  }

  @Override
  public boolean matches(Date value) {
    return value.before(to) || value.after(from);
  }
}
