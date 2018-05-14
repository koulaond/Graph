package repository.schema.introspection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Stream.of;

/**
 * Collector class that collects all fields from the given class and its superclasses.
 */
public class FieldCollector extends AbstractCollector<Field> {

    /**
     * {@inheritDoc}
     * @param declaringClass owner class
     * @param fieldMap values map
     */
    @Override
    protected void doCollect(Class declaringClass, Map<String, Field> fieldMap) {
        of(declaringClass.getDeclaredFields()).forEach(field -> {
            String fieldName = field.getName();
            if (!fieldMap.containsKey(fieldName)) {
                fieldMap.put(fieldName, field);
            }
        });
    }
}
