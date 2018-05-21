package repository.schema.introspection;

import static java.lang.String.format;
import static repository.schema.introspection.Constants.PREFIX_GET;
import static repository.schema.introspection.Constants.PREFIX_HAS;

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
}
