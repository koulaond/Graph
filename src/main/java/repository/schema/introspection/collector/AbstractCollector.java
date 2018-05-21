package repository.schema.introspection.collector;

import java.lang.reflect.AccessibleObject;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

/**
 * Abstract collector class for collecting reflecting accessible object from the owner class.
 *
 * @param <AO> accessible object type
 */
public abstract class AbstractCollector<AO extends AccessibleObject> implements Collector<AO> {

    /**
     * Collects all {@link AccessibleObject}s from the class and its superclasses.
     *
     * @param declaringClass owner class
     * @return map containing objectName:object values
     */
    @Override
    public Map<String, AO> collect(Class declaringClass) {
        if(declaringClass.isInterface() || Object.class.equals(declaringClass)){
            return emptyMap();
        }
        Map<String, AO> aoMap = new HashMap<>();
        doCollect(declaringClass, aoMap);

        Class superClass = declaringClass.getSuperclass();
        while (superClass != null && !Object.class.equals(superClass)) {
            doCollect(superClass, aoMap);
            superClass = superClass.getSuperclass();
        }
        return aoMap;
    }

    /**
     * Collects all {@link AccessibleObject}s from the class.
     *
     * @param declaringClass owner class
     * @param fieldMap values map
     */
    protected abstract void doCollect(Class declaringClass, Map<String, AO> fieldMap);
}
