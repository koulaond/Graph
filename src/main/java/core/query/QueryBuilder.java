package core.query;

import java.util.HashSet;
import java.util.Set;

import core.query.conditions.Condition;

public class QueryBuilder {
  private Set<Condition> conditions;

  private QueryBuilder() {
    this.conditions = new HashSet<>();
  }

  public QueryBuilder conditions(Set<Condition> conditions) {
    this.conditions.addAll(conditions);
    return this;
  }

  public QueryBuilder condition(Condition condition) {
    this.conditions.add(condition);
    return this;
  }

  public Query build() {
    return new Query(conditions);
  }

  public static QueryBuilder instance() {
    return new QueryBuilder();
  }
}