package core.schema.introspection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AnnotationMergerTest {

    @Test
    public void merge() throws Exception {
        Map<Field, Annotation> annForFields = new HashMap<>();
        Map<Method, Annotation> annForGetters = new HashMap<>();
        fillMaps(annForFields, annForGetters);
        AnnotationMerger merger = new AnnotationMerger();
        Map<String, Annotation> merged = merger.merge(annForFields, annForGetters);
        merged.forEach((fieldName, annotation) -> {
            AnnotationsGroup group = AnnotationsGroup.forFieldName(fieldName);
            switch (group.getAnnotationPlace()) {
                case FIELD_ONLY:
                    try {
                        assertThat(annotation).isEqualTo(annForFields.get(TestClass.class.getDeclaredField(fieldName)));
                    } catch (NoSuchFieldException e) {
                        fail("No field with name " + fieldName);
                    }
                    break;
                case GETTER_ONLY:
                case BOTH:
                    Method getter = findGetter(annForGetters, group.getGetterName());
                    assertThat(annotation).isEqualTo(annForGetters.get(getter));
                    break;
            }
        });

    }

    private Method findGetter(Map<Method, Annotation> annForGetter, String getterName) {
        return annForGetter.keySet()
                .stream()
                .filter(getter -> getter.getName().equals(getterName))
                .findFirst()
                .get();
    }

    private void fillMaps(Map<Field, Annotation> annForFields, Map<Method, Annotation> annForGetters) {
        Stream.of(AnnotationsGroup.values())
                .forEach(value -> {
                    switch (value.getAnnotationPlace()) {
                        case FIELD_ONLY:
                            adjustMapWithFields(value.getFieldName(), mock(Annotation.class), annForFields);
                            break;
                        case GETTER_ONLY:
                            adjustMapWithGetters(value.getGetterName(), mock(Annotation.class), annForGetters);
                            break;
                        case BOTH:
                            adjustMapWithFields(value.getFieldName(), mock(Annotation.class), annForFields);
                            adjustMapWithGetters(value.getGetterName(), mock(Annotation.class), annForGetters);
                            break;
                    }
                });
    }

    private void adjustMapWithFields(String fieldName, Annotation annotation, Map<Field, Annotation> annForFields) {
        Field field = obtainFieldFromTestClass(fieldName);
        annForFields.put(field, annotation);
    }

    private void adjustMapWithGetters(String getterName, Annotation annotation, Map<Method, Annotation> annForFields) {
        Method getter = obtainGetterFromTestClass(getterName);
        annForFields.put(getter, annotation);
    }

    private Method obtainGetterFromTestClass(String getterName) {
        return Stream.of(TestClass.class.getDeclaredMethods())
                .filter(getter -> getter.getName().equals(getterName))
                .findFirst()
                .get();
    }

    private Field obtainFieldFromTestClass(String fieldName) {
        return Stream.of(TestClass.class.getDeclaredFields())
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .get();
    }

    private enum AnnotationPlace {
        FIELD_ONLY, GETTER_ONLY, BOTH
    }

    private enum AnnotationsGroup {
        NAME("name", "getName", AnnotationPlace.FIELD_ONLY),
        ADDRESS("address", "getAddress", AnnotationPlace.FIELD_ONLY),
        IS_NEW("old", "isOld", AnnotationPlace.GETTER_ONLY),
        AGE("age", "getAge", AnnotationPlace.BOTH),
        HAS_PARENT("parent", "hasParent", AnnotationPlace.GETTER_ONLY);

        private String fieldName;
        private String getterName;
        private AnnotationPlace annotationPlace;

        AnnotationsGroup(String fieldName, String getterName, AnnotationPlace annotationPlace) {
            this.fieldName = fieldName;
            this.getterName = getterName;
            this.annotationPlace = annotationPlace;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getGetterName() {
            return getterName;
        }

        public AnnotationPlace getAnnotationPlace() {
            return annotationPlace;
        }

        public static AnnotationsGroup forGetterName(String getterName) {
            return Stream.of(values())
                    .filter(value -> value.getGetterName().equals(getterName))
                    .findFirst()
                    .get();
        }

        public static AnnotationsGroup forFieldName(String fieldName) {
            return Stream.of(values())
                    .filter(value -> value.getFieldName().equals(fieldName))
                    .findFirst()
                    .get();
        }
    }

    /**
     * I created this test class only for obtain {@link Field} objects, because Field class is final
     * and cannot be mocked.
     */
    private class TestClass {

        private String name, address;

        private int age;

        private boolean old, parent;

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public int getAge() {
            return age;
        }

        public boolean isOld() {
            return old;
        }

        public boolean hasParent() {
            return parent;
        }
    }
}