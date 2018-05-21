package repository.schema.introspection;

import org.junit.Test;
import repository.schema.annotations.Node;
import repository.schema.annotations.properties.NumericProperty;
import repository.schema.annotations.properties.StringProperty;

import java.util.Date;

public class NodeIntrospectorTest {

    @Test(expected = IllegalStateException.class)
    public void whenClassHasNoNodeAnnotation_thenThrowException() throws Exception {
        new NodeIntrospector<>(TestClassNoAnnotation.class).introspect();
    }

    @Test(expected = IllegalStateException.class)
    public void whenClassHasNoMultipleAnnotations_thenThrowException() throws Exception {

    }

    @Node(immutable = true, nodeType = "TestClass", maxCount = 42)
    private class TestClass {

        @StringProperty(nonNull = true, name = "name", maxLength = 256)
        private String name;

        @NumericProperty
        private Long size;
        private TestClass sibling;
        private int day;
        private Date date;
    }

    private class TestClassNoAnnotation {

    }
}