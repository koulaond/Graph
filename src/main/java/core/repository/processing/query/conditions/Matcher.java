package core.repository.processing.query.conditions;

import java.io.Serializable;

public interface Matcher<V extends Serializable> {

  boolean matches(V value);
}
