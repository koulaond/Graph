package core.repository.processing.query.conditions;

import java.io.Serializable;

public class Condition<V extends Serializable> {
   private V value;
   private Matcher<V> matcher;

   Condition(V value, Matcher<V> matcher) {
      this.value = value;
      this.matcher = matcher;
   }

   public V value() {
      return value;
   }

   public boolean matches() {
      return matcher.matches(value);
   }
}
