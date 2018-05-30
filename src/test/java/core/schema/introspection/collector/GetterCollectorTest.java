package core.schema.introspection.collector;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


public class GetterCollectorTest {

    /**
     * Expected result map size;
     */
    private static final int SIZE = 7;

    /**
     * Fields names.
     */
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_READY = "ready";
    private static final String FIELD_COUNT = "count";
    private static final String FIELD_PRECISE = "precise";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_SIBLING = "sibling";
    private static final String FIELD_CHUCK_NORRIS = "chuckNorris";

    /**
     * Fields getters names.
     */
    private static final String GETTER_ID = "getId";
    private static final String GETTER_NAME = "getName";
    private static final String GETTER_READY = "isReady";
    private static final String GETTER_COUNT = "getCount";
    private static final String GETTER_PRECISE = "getPrecise";
    private static final String GETTER_TYPE = "getType";
    private static final String GETTER_SIBLING = "getSibling";
    private static final String GETTER_CHUCK_NORRIS = "getChuckNorris";

    @Test
    public void doCollect() throws Exception {
        GetterCollector getterCollector = new GetterCollector();
        Class<TestLowerLevelClass> testLowerClass = TestLowerLevelClass.class;
        Class<TestLowestLevelClass> testLowestClass = TestLowestLevelClass.class;
        Map<String, Method> getterMap = getterCollector.collect(testLowestClass);
        assertThat(getterMap)
                .isNotNull()
                .hasSize(SIZE)
                .satisfies(map -> {
                    try {
                        assertThat(map.get(FIELD_ID))
                                .isNotNull()
                                .isEqualTo(testLowerClass.getDeclaredMethod(GETTER_ID));

                        assertThat(map.get(FIELD_NAME))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredMethod(GETTER_NAME))
                                .isNotEqualTo(testLowerClass.getDeclaredMethod(GETTER_NAME));

                        assertThat(map.get(FIELD_READY))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredMethod(GETTER_READY))
                                .isNotEqualTo(testLowerClass.getDeclaredMethod(GETTER_READY));

                        assertThat(map.get(FIELD_COUNT))
                                .isNotNull()
                                .isEqualTo(testLowerClass.getDeclaredMethod(GETTER_COUNT));

                        assertThat(map.get(FIELD_PRECISE))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredMethod(GETTER_PRECISE))
                                .isNotEqualTo(testLowerClass.getDeclaredMethod(GETTER_PRECISE));

                        assertThat(map.get(FIELD_TYPE))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredMethod(GETTER_TYPE));

                        assertThat(map.get(FIELD_SIBLING))
                                .isNotNull()
                                .isEqualTo(testLowestClass.getDeclaredMethod(GETTER_SIBLING));

                        assertThat(map.get(FIELD_CHUCK_NORRIS))
                                .isNull();

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                        fail(format("Cannot obtain getter from test class. %s", e.getMessage()));
                    }
                });
    }

    private class TestLowerLevelClass {

        /**
         * Parent fields.
         */
        protected Long id;
        protected String name;
        protected boolean ready;
        protected int count;
        protected float precise;

        /**
         * Getters to be collected.
         */
        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean isReady() {
            return ready;
        }

        public int getCount() {
            return count;
        }

        public float getPrecise() {
            return precise;
        }
    }

    private class TestLowestLevelClass extends TestLowerLevelClass {

        /**
         * Additional fields.
         */
        private String type;
        private TestLowerLevelClass sibling;

        /**
         * Overridden getters.
         */
        public String getName() {
            return name;
        }

        public boolean isReady() {
            return ready;
        }

        public float getPrecise() {
            return precise;
        }

        /**
         * Additional getters.
         */
        public String getType() {
            return type;
        }

        public TestLowerLevelClass getSibling() {
            return sibling;
        }

        /**
         * This method is not a getter so it must not be collected.
         */
        public void getChuckNorris(){
            // Do nothing.
        }
    }
}