package repository.schema.introspection;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing whether the collector returns correct fields from the declaring class.
 */
public class FieldCollectorTest {

    /**
     * Expected result map size;
     */
    private static final int COUNT = 5;

    /**
     * Expected fields names.
     */
    private static final String FIELD_NAME = "name";
    private static final String FIELD_IDENTIFIER = "identifier";
    private static final String FIELD_CODE = "code";
    private static final String FIELD_COUNT = "count";
    private static final String FIELD_READY = "ready";

    @Test
    public void doCollect() throws Exception {
        FieldCollector collector = new FieldCollector();
        Map<String, Field> fieldMap = collector.collect(TestLowestLevelClass.class);
        Class<TestLowerLevelClass> testLowerClass = TestLowerLevelClass.class;
        Class<TestLowestLevelClass> testLowestClass = TestLowestLevelClass.class;
        assertThat(fieldMap)
                .isNotNull()
                .hasSize(COUNT)
                .satisfies((Map<String, Field> map) -> {
                    try {
                        assertThat(map.get(FIELD_NAME))
                                .isNotNull()
                                .isEqualTo(testLowerClass.getDeclaredField(FIELD_NAME));

                        assertThat(map.get(FIELD_IDENTIFIER))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredField(FIELD_IDENTIFIER))
                                .isNotEqualTo(testLowerClass.getDeclaredField(FIELD_IDENTIFIER));

                        assertThat(map.get(FIELD_CODE))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredField(FIELD_CODE))
                                .isNotEqualTo(testLowerClass.getDeclaredField(FIELD_CODE));

                        assertThat(map.get(FIELD_COUNT))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredField(FIELD_COUNT));

                        assertThat(map.get(FIELD_READY))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredField(FIELD_READY));
                    } catch (NoSuchFieldException e) {
                        Assertions.fail(format("Cannot obtain field from test class. %s", e.getMessage()));
                    }
                });
    }

    /**
     * Testing lower level class.
     */
    private class TestLowerLevelClass {
        private String name;
        private String identifier;  // Should be overridden by identifier in lowest class
        private Long code;  // Should be overridden by code in lowest class
    }

    /**
     * Testing lowest level class.
     */
    private class TestLowestLevelClass extends TestLowerLevelClass {
        private Object identifier;
        private int count;
        private Long code;
        private boolean ready;
    }

}