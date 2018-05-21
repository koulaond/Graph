package repository.schema.introspection.collector;

import java.lang.reflect.AccessibleObject;
import java.util.Map;

public interface Collector<AO extends AccessibleObject> {

    Map<String, AO> collect(Class declaringClass);
}
