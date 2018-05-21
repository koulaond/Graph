package repository.schema.introspection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.assertj.core.api.Assertions.assertThat;

public class AnnotationIntrospectorTest {

    /**
     * Expected result collection size.
     */
    private static final int SIZE = 3;

    @Test
    public void introspectAnnotations() throws Exception {
        Collection<Field> fields = of(TestClass.class.getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .collect(toList());
        Map<Field, Annotation> fieldAnnotations = new AnnotationIntrospector<>(fields)
                .introspectAnnotations(annotation -> TestAnnotation.class.equals(annotation.annotationType()));

        assertThat(fieldAnnotations)
                .isNotNull()
                .hasSize(SIZE)
                .satisfies(map -> {
                    assertThat(map.values())
                            .doesNotContainNull();
                });
    }

    private class TestClass {

        @TestAnnotation
        private String name;

        @TestAnnotation
        private Long size;

        private int count;

        @TestAnnotation
        private TestClass sibling;
    }

    @Retention(RetentionPolicy.RUNTIME)
    private @interface TestAnnotation {

    }
}