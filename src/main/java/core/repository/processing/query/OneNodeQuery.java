package core.repository.processing.query;

import java.util.HashSet;
import java.util.Set;

import core.repository.processing.query.conditions.Condition;

/**
 * Query for receive single node matching predefined conditions.
 */
public class OneNodeQuery {
  private Set<Condition> conditions;

  OneNodeQuery(Set<Condition> conditions) {
    this.conditions = conditions;
  }

  public Set<Condition> getConditions() {
    return conditions;
  }

  public static OneNodeQueryBuilder builder() {
    return new OneNodeQueryBuilder();
  }

  /**
   * Builder for {@link OneNodeQuery}.
   */
  public static class OneNodeQueryBuilder {
    private Set<Condition> conditions;

    OneNodeQueryBuilder() {
      this.conditions = new HashSet<>();
    }

    public OneNodeQueryBuilder conditions(Set<Condition> conditions) {
      this.conditions.addAll(conditions);
      return this;
    }

    public OneNodeQueryBuilder condition(Condition condition) {
      this.conditions.add(condition);
      return this;
    }

    public OneNodeQuery build() {
      return new OneNodeQuery(conditions);
    }
  }
}
