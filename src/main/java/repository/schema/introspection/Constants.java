package repository.schema.introspection;

import static java.lang.String.format;

public class Constants {

    public static final String ERROR_DUPLICATE_PROPS_ON_FIELD = "Cannot process class %s. There is property annotations collision on field %s. Multiple property annotations are defined: %s";
    public static final String ERROR_DUPLICATE_PROPS_ON_GETTER = "Cannot process class %s. There is property annotations collision on getter method %s. Multiple property annotations are defined: %s";
    public static final String PREFIX_IS = "is";
    public static final String PREFIX_HAS = "has";
    public static final String PREFIX_GET = "get";

    private Constants() {
        throw new IllegalStateException(format("No instances of %s !!", getClass().getName()));
    }
}
