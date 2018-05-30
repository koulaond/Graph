package core.schema.introspection.collector;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test for @{@link AbstractCollector} class. It tests only {@code collect} method logic - this method calls
 * {@code doCollect} method for a class on each level of class hierarchy except @{@link Object} level.
 * If the target class is an @{@link Object} or is an interface then empty map is returned.
 */
public class AbstractCollectorTest {

    /**
     * Number of invocations for testing classes hierarchy.
     */
    private static final int INVOCATIONS_COUNT_HIERACRHY = 3;
    private static final int INVOCATIONS_COUNT_INTERFACE_OBJECT = 0;

    /**
     * Tested @{@link AbstractCollector} object.
     */
    private AbstractCollector collector;

    @Before
    public void setup() {
        collector = Mockito.spy(new AbstractCollector() {
            @Override
            protected void doCollect(Class declaringClass, Map fieldMap) {
                // Do nothing
            }
        });
    }

    @Test
    public void whenClassWithTwoSuperclassesIsUsed_thenDoCollectIsCalledThreeTImes() throws Exception {
        collector.collect(TestLowestLevelClass.class);
        verify(collector, times(INVOCATIONS_COUNT_HIERACRHY)).doCollect(any(), anyMap());
    }

    @Test
    public void whenInterfaceIsUsed_thenDoCollectIsNotCalled() throws Exception {
        collector.collect(TestInterface.class);
        verify(collector, times(INVOCATIONS_COUNT_INTERFACE_OBJECT)).doCollect(any(), anyMap());
    }

    @Test
    public void whenObjectClassIsUsed_thenDoCollectIsNotCalled() throws Exception {
        collector.collect(Object.class);
        verify(collector, times(INVOCATIONS_COUNT_INTERFACE_OBJECT)).doCollect(any(), anyMap());
    }

    private class TestSuperClass {
        // Testing super class
    }

    private class TestLowerLevelClass extends TestSuperClass {
        // Testing lower level class
    }

    private class TestLowestLevelClass extends TestLowerLevelClass {
        // Testing lowest level class
    }

    private interface TestInterface {
        // Testing interface
    }

}