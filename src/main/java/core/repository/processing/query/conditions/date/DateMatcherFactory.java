package core.repository.processing.query.conditions.date;

import java.util.Date;

import core.repository.processing.query.conditions.Matcher;

public class DateMatcherFactory {

  public Matcher<Date> before(Date to) {
    return new BeforeDateMatcher(to);
  }

  public Matcher<Date> after(Date from) {
    return new AfterDateMatcher(from);
  }

  public Matcher<Date> between(Date from, Date to) {
    return new BetweenDateMatcher(from, to);
  }

  public Matcher<Date> outside(Date to, Date from) {
    return new OutsideDateMatcher(to, from);
  }
}
