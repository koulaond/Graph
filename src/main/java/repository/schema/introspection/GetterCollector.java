package repository.schema.introspection;

import java.lang.reflect.Method;
import java.util.Map;

import static java.util.stream.Stream.of;
import static repository.schema.annotations.properties.PropertyDeclaration.hasPropertyAnnotation;
import static repository.schema.introspection.Constants.PREFIX_GET;
import static repository.schema.introspection.Constants.PREFIX_HAS;
import static repository.schema.introspection.Constants.PREFIX_IS;

public class GetterCollector extends AbstractCollector<Method> {

    @Override
    protected void doCollect(Class declaringClass, Map<String, Method> map) {
        of(declaringClass.getDeclaredMethods()).forEach(method -> {
            String methodName = convertGetterNameToFieldName(method.getName());
            if (!map.containsKey(methodName) && isGetter(method) && hasPropertyAnnotation(method)) {
                map.put(methodName, method);
            }
        });
    }

    private String convertGetterNameToFieldName(String getterName) {
        String fieldName = null;
        if (getterName.startsWith(PREFIX_GET) || getterName.startsWith(PREFIX_HAS)) {
            fieldName = getterName.substring(3);
        } else if (getterName.startsWith(Constants.PREFIX_IS)) {
            fieldName = getterName.substring(2);
        }
        return uncapitalize(fieldName);
    }

    private boolean isGetter(Method method) {
        String name = method.getName();
        Class returnType = method.getReturnType();
        return (name.startsWith(PREFIX_GET) || name.startsWith(PREFIX_HAS) || name.startsWith(PREFIX_IS))
                && !returnType.equals(Void.class)
                && method.getParameterCount() == 0;
    }

    private String uncapitalize(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
