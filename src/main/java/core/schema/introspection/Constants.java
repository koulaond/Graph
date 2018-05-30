package core.schema.introspection;

import static java.lang.String.format;

public class Constants {

    public static final String ERROR_DUPLICATE_ANNOTATIONS = "Duplicate annotations %s declared on %s.";
    public static final String ERROR_MISSING_ANNOTATION = " Missing annotation %s on %s.";
    public static final String PREFIX_IS = "is";
    public static final String PREFIX_HAS = "has";
    public static final String PREFIX_GET = "get";

    private Constants() {
        throw new IllegalStateException(format("No instances of %s !!", getClass().getName()));
    }
}
