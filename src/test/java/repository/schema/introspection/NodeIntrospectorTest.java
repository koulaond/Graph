package repository.schema.introspection;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import repository.schema.annotations.Node;
import repository.schema.annotations.Relationship;
import repository.schema.annotations.properties.DateProperty;
import repository.schema.annotations.properties.NumericProperty;
import repository.schema.annotations.properties.StringProperty;
import repository.schema.descriptions.NodeDescription;

import java.util.Date;

public class NodeIntrospectorTest {

    @Test(expected = IllegalStateException.class)
    public void testWhenClassHasNoNodeAnnotation_thenThrowException() throws Exception {
        new NodeIntrospector(TestClassNoAnnotation.class).introspect();
    }

    @Test
    public void testWhenClassIsCorrectlyDefined_thenResultIsReturned() throws Exception {
        NodeIntrospector introspector = new NodeIntrospector(ExtendedTestClass.class);
        NodeDescription<ExtendedTestClass> description = introspector.introspect();
        Assertions.assertThat(description)
                .isNotNull();
    }

    @Node(immutable = true, nodeType = "TestClass", maxCount = 42)
    private class TestClass {

        @StringProperty(nonNull = true, name = "_name", maxLength = 256)
        protected String name;

        @NumericProperty
        protected Long size;

        @Relationship(referencedClass = TestClass.class, name = "is_sibling_with")
        protected TestClass sibling;

        @NumericProperty
        protected int day;

        @DateProperty
        protected Date date;

        @NumericProperty
        protected int depth;

        public String getName() {
            return name;
        }

        // This annotation must override the field annotation
        @NumericProperty(name = "length", nonNull = true)
        public Long getSize() {
            return size;
        }

        public TestClass getSibling() {
            return sibling;
        }

        // This annotation must override the field annotation
        @NumericProperty(name = "_day", immutable = true)
        public int getDay() {
            return day;
        }

        public Date getDate() {
            return date;
        }
    }

    private class ExtendedTestClass extends TestClass {
        private String extendedInfo;

        // This field changes and overrides the 'date' field in superclass - his annotation is changed to String
        @StringProperty(name = "date", nonNull = true)
        public Date getDate() {
            return date;
        }

        @StringProperty
        public String getExtendedInfo() {
            return extendedInfo;
        }

        @NumericProperty(name = "_depth")
        public int getDepth(){
            return depth;
        }
    }

    private class TestClassNoAnnotation {

    }
}