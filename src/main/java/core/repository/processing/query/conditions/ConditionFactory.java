package core.repository.processing.query.conditions;

import java.io.Serializable;

import core.repository.processing.query.conditions.matchers.Matcher;

public class ConditionFactory {

  public <V extends Serializable> Condition<V>  atomic(V value, Matcher<V> matcher){
    return new Condition<>(value, matcher);
  }
}
