package repository.schema.introspection;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Stream.of;
import static repository.schema.introspection.PropertyDeclaration.hasPropertyAnnotation;
import static repository.schema.introspection.Constants.PREFIX_GET;
import static repository.schema.introspection.Constants.PREFIX_HAS;
import static repository.schema.introspection.Constants.PREFIX_IS;
import static repository.schema.introspection.Utils.convertGetterNameToFieldName;

/**
 * Collector class that collects all getters from the given class and its superclasses.
 */
public class GetterCollector extends AbstractCollector<Method> {

    /**
     * {@inheritDoc}
     * @param declaringClass owner class
     * @param getterMap values map
     */
    @Override
    protected void doCollect(Class declaringClass, Map<String, Method> getterMap) {
        of(declaringClass.getDeclaredMethods()).forEach(method -> {
            String methodName = convertGetterNameToFieldName(method.getName());
            if (!getterMap.containsKey(methodName) && isGetter(method)) {
                getterMap.put(methodName, method);
            }
        });
    }

    /**
     * Returns whether method is getter or not.
     * Method is getter if meets these conditions:
     * 1. Method's name starts with 'get', 'is' or 'has'
     * 2. Method musts return an object so its return type must not be void
     * 3. Method does not have parameters
     * @param method method instance
     * @return true when the method is getter. Otherwise it returns false.
     */
    private boolean isGetter(Method method) {
        String name = method.getName();
        Class returnType = method.getReturnType();
        return (name.startsWith(PREFIX_GET) || name.startsWith(PREFIX_HAS) || name.startsWith(PREFIX_IS))
                && !returnType.equals(Void.class)
                && method.getParameterCount() == 0;
    }
}
