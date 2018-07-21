package core.schema.introspection;

import org.junit.Test;
import core.schema.annotations.Node;
import core.schema.annotations.PropertyHolder;
import core.schema.annotations.Relationship;
import core.schema.annotations.properties.DateProperty;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;
import core.schema.descriptions.*;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

public class NodeIntrospectorTest {

    private static final int PROP_DESCRIPTIONS_SIZE = 6;
    private static final int REL_DESCRIPTIONS_SIZE = 1;
    private static final int PROP_HOLDER_PROPS_SIZE = 1;

    private static final String NODE_TYPE = "_TestClass";
    private static final String PROP_NAME = "_name";
    private static final String PROP_SIZE = "size";
    private static final String PROP_DAY = "_day";
    private static final String PROP_DATE = "_date";
    private static final String PROP_DEPTH = "_depth";
    private static final String PROP_EXTENDED_INFO = "_extendedInfo";

    private static final String REL_SIBLING = "is_sibling_with";

    private static final String PROP_HOLDER_PROP_NAME = "_name";
    private static final boolean PROP_HOLDER_PROP_NAME_NON_NULL = true;
    private static final boolean PROP_HOLDER_PROP_NAME_IMMUTABLE = true;
    private static final int NODE_MAX_COUNT = 42;
    private static final boolean NODE_IS_IMMUTABLE = true;
    private static final int PROP_NAME_MAX_LENGTH = 256;
    private static final boolean PROP_NAME_NON_NULL = true;
    private static final boolean PROP_SIZE_NON_NULL = true;
    private static final boolean PROP_DAY_IMMUTABLE = true;
    private static final boolean PROP_DATE_NON_NULL = true;

    @Test(expected = IllegalStateException.class)
    public void testWhenClassHasNoNodeAnnotation_thenThrowException() throws Exception {
        new NodeIntrospector(TestClassNoAnnotation.class).introspect();
    }

    @Test
    public void testWhenClassIsCorrectlyDefined_thenResultIsReturned() throws Exception {
        NodeIntrospector introspector = new NodeIntrospector(ExtendedTestClass.class);
        NodeDescription<ExtendedTestClass> nodeDescription = introspector.introspect();
        assertThat(nodeDescription)
                .isNotNull()
                .satisfies(nodeDesc -> {
                    // Node attributes test
                    assertThat(nodeDesc.getDescribedClass()).isEqualTo(ExtendedTestClass.class);
                    assertThat(nodeDesc.getMaxCount()).isEqualTo(NODE_MAX_COUNT);
                    assertThat(nodeDesc.getType()).isEqualTo(NODE_TYPE);
                    assertThat(nodeDesc.isImmutable()).isEqualTo(NODE_IS_IMMUTABLE);

                    // Properties attributes test
                    assertThat(nodeDesc.getPropertyDescriptions())
                            .isNotNull()
                            .hasSize(PROP_DESCRIPTIONS_SIZE)
                            .satisfies(propDescs -> {
                                Map<String, PropertyDescription> collectedProps = ((Set<PropertyDescription>) propDescs)
                                        .stream()
                                        .collect(toMap(PropertyDescription::getPropertyName, Function.identity()));

                                assertThat(collectedProps.get(PROP_NAME))
                                        .isNotNull()
                                        .isInstanceOf(StringPropertyDescription.class)
                                        .satisfies(prop -> {
                                            assertThat(prop.getPropertyName()).isEqualTo(PROP_NAME);
                                            assertThat(((StringPropertyDescription) prop).getMaxLength())
                                                    .isEqualTo(PROP_NAME_MAX_LENGTH);
                                            assertThat(prop.isMandatory()).isEqualTo(PROP_NAME_NON_NULL);
                                        });

                                assertThat(collectedProps.get(PROP_SIZE))
                                        .isNotNull()
                                        .isInstanceOf(NumericPropertyDescription.class)
                                        .satisfies(prop -> {
                                            assertThat(prop.getPropertyName()).isEqualTo(PROP_SIZE);
                                            assertThat(prop.isMandatory()).isEqualTo(PROP_SIZE_NON_NULL);
                                        });

                                assertThat(collectedProps.get(PROP_DAY))
                                        .isNotNull()
                                        .isInstanceOf(NumericPropertyDescription.class)
                                        .satisfies(prop -> {
                                            assertThat(prop.getPropertyName()).isEqualTo(PROP_DAY);
                                            assertThat(prop.isImmutable()).isEqualTo(PROP_DAY_IMMUTABLE);
                                        });

                                assertThat(collectedProps.get(PROP_DATE))
                                        .isNotNull()
                                        .isInstanceOf(StringPropertyDescription.class)
                                        .satisfies(prop -> {
                                            assertThat(prop.getPropertyName()).isEqualTo(PROP_DATE);
                                            assertThat(prop.isMandatory()).isEqualTo(PROP_DATE_NON_NULL);
                                        });

                                assertThat(collectedProps.get(PROP_DEPTH))
                                        .isNotNull()
                                        .isInstanceOf(NumericPropertyDescription.class)
                                        .satisfies(prop -> {
                                            assertThat(prop.getPropertyName()).isEqualTo(PROP_DEPTH);
                                        });

                                assertThat(collectedProps.get(PROP_EXTENDED_INFO))
                                        .isNotNull()
                                        .isInstanceOf(StringPropertyDescription.class)
                                        .satisfies(prop -> {
                                            assertThat(prop.getPropertyName()).isEqualTo(PROP_EXTENDED_INFO);
                                        });
                            });

                    //  Relationship descriptions test
                    assertThat(nodeDesc.getRelationshipDescriptions())
                            .isNotNull()
                            .hasSize(REL_DESCRIPTIONS_SIZE)
                            .satisfies(rels -> {
                                assertThat(rels.iterator().next())
                                        .isNotNull()
                                        .satisfies(relation -> {
                                            assertThat(relation.getPropertyName()).isEqualTo(REL_SIBLING);
                                            assertThat(relation.getReferencedClass()).isEqualTo(TestClass.class);
                                            assertThat(relation.getPropertyDescriptions())
                                                    .isNotNull()
                                                    .hasSize(PROP_HOLDER_PROPS_SIZE);
                                            assertThat(relation.getPropertyDescriptions().iterator().next())
                                                    .isInstanceOf(StringPropertyDescription.class)
                                                    .satisfies(propDesc -> {
                                                        StringPropertyDescription cast = (StringPropertyDescription) propDesc;
                                                        assertThat(cast.getPropertyName())
                                                                .isEqualTo(PROP_HOLDER_PROP_NAME);
                                                        assertThat(cast.isMandatory())
                                                                .isEqualTo(PROP_HOLDER_PROP_NAME_NON_NULL);
                                                        assertThat(cast.isImmutable())
                                                                .isEqualTo(PROP_HOLDER_PROP_NAME_IMMUTABLE);
                                                    });
                                        });
                            });
                });
    }

    @Node(immutable = NODE_IS_IMMUTABLE, nodeType = NODE_TYPE, maxCount = NODE_MAX_COUNT)
    private class TestClass {

        @StringProperty(nonNull = PROP_NAME_NON_NULL, name = PROP_NAME, maxLength = PROP_NAME_MAX_LENGTH)
        protected String name;

        @NumericProperty
        protected Long size;

        @Relationship(referencedClass = TestClass.class, propertyHolderClass = PropertyHolderClass.class, name = REL_SIBLING)
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
        @NumericProperty(nonNull = true)
        public Long getSize() {
            return size;
        }

        public TestClass getSibling() {
            return sibling;
        }

        // This annotation must override the field annotation
        @NumericProperty(name = PROP_DAY, immutable = true)
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
        @StringProperty(name = PROP_DATE, nonNull = true)
        public Date getDate() {
            return date;
        }

        @StringProperty(name = PROP_EXTENDED_INFO)
        public String getExtendedInfo() {
            return extendedInfo;
        }

        // This field changes and overrides the 'depth' field in superclass
        @NumericProperty(name = PROP_DEPTH)
        public int getDepth() {
            return depth;
        }
    }

    @PropertyHolder
    private class PropertyHolderClass {

        @StringProperty(name = PROP_HOLDER_PROP_NAME,
                nonNull = PROP_HOLDER_PROP_NAME_NON_NULL,
                immutable = PROP_HOLDER_PROP_NAME_IMMUTABLE)
        private String name;
    }

    private class TestClassNoAnnotation {

    }
}