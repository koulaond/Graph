package core.repository.processing.query;

import java.util.Set;

import core.repository.processing.query.conditions.Condition;

public class Query {
  private Set<Condition> conditions;

  Query(Set<Condition> conditions) {
    this.conditions = conditions;
  }

  public Set<Condition> getConditions() {
    return conditions;
  }
}
