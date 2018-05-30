package core.schema.introspection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static core.schema.introspection.Constants.PREFIX_GET;
import static core.schema.introspection.Constants.PREFIX_HAS;

public class Utils {

    private Utils() {
        throw new IllegalStateException(format("No instances of %s !!", getClass().getName()));
    }

    /**
     * Converts getter name to particular field name. 'getField' | 'isField' | 'hasField' -> 'field'
     * @param getterName getter name
     * @return field name
     */
    public static String convertGetterNameToFieldName(String getterName) {
        String fieldName = null;
        if (getterName.startsWith(PREFIX_GET) || getterName.startsWith(PREFIX_HAS)) {
            fieldName = getterName.substring(3);
        } else if (getterName.startsWith(Constants.PREFIX_IS)) {
            fieldName = getterName.substring(2);
        }
        return uncapitalize(fieldName);
    }

    /**
     * Uncapitalizes the input string
     * @param str string to be uncapitalized
     * @return uncapitalized string
     */
    public static String uncapitalize(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * Returns whether field type is multi-value. That means the type is a collection.
     * @param field checked field
     */
    public static boolean isFieldTypeMultiValue(Field field) {
        return isTypeMultiValue(requireNonNull(field).getType());
    }

    /**
     * Returns whether method return type is multi-value. That means the type is a collection.
     * @param method checked method
     */
    public static boolean isMethodReturnTypeMultiValue(Method method) {
        return isTypeMultiValue(requireNonNull(method).getReturnType());
    }

    /**
     * Returns whether class type is multi-value. That means the type is a collection.
     * @param clazz checked class
     */
    public static boolean isTypeMultiValue(Class<?> clazz) {
        return Collection.class.isAssignableFrom(requireNonNull(clazz));
    }
}
