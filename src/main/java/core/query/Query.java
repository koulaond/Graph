package core.query;

import java.util.Set;

import core.query.conditions.Condition;

public class Query {
  private Set<Condition> conditions;

  Query(Set<Condition> conditions) {
    this.conditions = conditions;
  }

  public Set<Condition> getConditions() {
    return conditions;
  }
}
