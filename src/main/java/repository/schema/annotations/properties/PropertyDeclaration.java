package repository.schema.annotations.properties;


import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public enum PropertyDeclaration {
    DATE_PROP(DateProperty.class),
    ENUM_PROP(EnumProperty.class),
    NUMERIC_PROP(NumericProperty.class),
    TEXT_PROP(TextProperty.class);

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
}
