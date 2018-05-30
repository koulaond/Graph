package core.schema.introspection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import core.schema.annotations.Node;
import core.schema.descriptions.NodeDescription;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PackageIntrospectorTest {

    private static final int DESCRIPTIONS_SIZE = 3;

    private static Set<Class<?>> testClasses;

    @Spy
    private PackageIntrospector introspector;

    @BeforeClass
    public static void setupClass() {
        testClasses = new HashSet<>();
        testClasses.add(TestClassJoe.class);
        testClasses.add(TestClassChuck.class);
        testClasses.add(TestClassArnold.class);
    }

    @Before
    public void setup() {
        when(introspector.getClasses(anyString())).thenReturn(testClasses);
    }

    @Test
    public void introspectPackage() throws Exception {
        Set<NodeDescription> descriptions = introspector.introspectPackage(new String());
        assertThat(descriptions)
                .isNotNull()
                .hasSize(DESCRIPTIONS_SIZE)
                .satisfies(descrs -> {
                    Map<Class<?>, NodeDescription> collected = ((Set<NodeDescription>) descrs)
                            .stream()
                            .collect(toMap(description -> description.getDescribedClass(), identity()));
                    testClasses.forEach(testClass -> assertThat(collected.get(testClass)).isNotNull());
                });
    }

    @Node
    private class TestClassJoe {
        // Empty class
    }

    @Node
    private class TestClassChuck {
        // Empty class
    }

    @Node
    private class TestClassArnold {
        // Empty class
    }
}