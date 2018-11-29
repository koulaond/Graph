package core.repository.processing.query.conditions;

import java.io.Serializable;

import core.repository.processing.query.conditions.matchers.Matcher;

public class Condition<V extends Serializable> {
   private String propertyName;
   private Matcher<V> matcher;

   Condition(String propertyName, Matcher<V> matcher) {
      this.propertyName = propertyName;
      this.matcher = matcher;
   }

   public String getPropertyName() {
      return propertyName;
   }

   public Matcher<V> matcher() {
      return matcher;
   }
}
