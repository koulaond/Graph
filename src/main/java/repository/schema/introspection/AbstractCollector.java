package repository.schema.introspection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCollector<AO extends AccessibleObject> implements Collector<AO> {

    @Override
    public Map<String, AO> collect(Class declaringClass) {
        Map<String, AO> fieldMap = new HashMap<>();
        doCollect(declaringClass, fieldMap);

        Class superClass = declaringClass.getSuperclass();
        while (superClass != null && !Object.class.equals(superClass)) {
            doCollect(superClass, fieldMap);
            superClass = superClass.getSuperclass();
        }
        return fieldMap;
    }

    protected abstract void doCollect(Class declaringClass, Map<String, AO> fieldMap);
}
