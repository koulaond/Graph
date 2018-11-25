package core.repository.processing.query.conditions;

import java.io.Serializable;

public class ConditionFactory {

  public <V extends Serializable> Condition<V> textCondition(V value, Matcher<V> matcher){
    return new Condition<>(value, matcher);
  }
}
