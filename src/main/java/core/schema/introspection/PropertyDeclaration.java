package core.schema.introspection;


import core.schema.annotations.properties.DateProperty;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public enum PropertyDeclaration {
    DATE_PROP(DateProperty.class),
    ENUM_PROP(EnumProperty.class),
    NUMERIC_PROP(NumericProperty.class),
    TEXT_PROP(StringProperty.class);

    private Class<? extends Annotation> annotationClass;

    PropertyDeclaration(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public static boolean isPropertyAnnotation(Annotation annotation){
        PropertyDeclaration[] values = values();
        return Stream.of(values)
                .anyMatch(declaration -> declaration.annotationClass.equals(annotation.annotationType()));
    }

    public static boolean hasPropertyAnnotation(Method method){
        return Stream.of(method.getAnnotations()).anyMatch(PropertyDeclaration::isPropertyAnnotation);
    }
}
