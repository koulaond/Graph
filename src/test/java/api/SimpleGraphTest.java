package api;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class SimpleGraphTest {

    private static final UUID LEFT_VERTEX_UUID = UUID.randomUUID();
    private static final UUID RIGHT_VERTEX_UUID = UUID.randomUUID();

    private TestVertex left;

    private TestVertex right;

    private TestEdge testEdge;

    SimpleGraph<TestVertex, TestEdge> graph;

    @Before
    public void setup() {
        left = vertex(LEFT_VERTEX_UUID);
        right = vertex(RIGHT_VERTEX_UUID);
        testEdge = edge(left, right);
        graph = new SimpleGraph<>();
    }

    @Test
    public void createEdge() throws Exception {
        graph.insertVertices(left, right);
        graph.createEdge(() -> testEdge);

        assertThat(testEdge).satisfies(edge -> {
            assertThat(left).as("Left vertex does not contain an edge")
                    .satisfies(vertex -> verify(vertex).addEdge(any(TestEdge.class)));
            assertThat(right).as("Right vertex does not contain an edge")
                    .satisfies(vertex -> verify(vertex).addEdge(any(TestEdge.class)));

        });
    }

    @Test
    public void containsEdge_positive() throws Exception {
        graph.insertVertices(left, right);
        graph.createEdge(() -> testEdge);
        assertThat(graph).as("Graph does not contain particular edge")
                .satisfies(graph -> assertTrue(graph.containsEdge(testEdge)));
    }

    @Test
    public void containsEdge_negative() throws Exception {
        assertThat(graph).as("Graph SHOULD not contain particular edge")
                .satisfies(graph -> assertFalse(graph.containsEdge(testEdge)));
    }

    @Test
    public void containsEdgeForVertices_positive() throws Exception {
        graph.insertVertices(left, right);
        graph.createEdge(() -> testEdge);
        when(testEdge.isForVertices(left, right)).thenReturn(true);
        assertThat(graph).as("Graph does not contain particular edge for given vertices")
                .satisfies(graph -> assertTrue(graph.containsEdgeForVertices(left, right)));
    }

    @Test
    public void containsEdgeForVertices_negative() throws Exception {
        when(testEdge.isForVertices(left, right)).thenReturn(false);
        assertThat(graph).as("Graph SHOULD not contain particular edge for given vertices")
                .satisfies(graph -> assertFalse(graph.containsEdgeForVertices(left, right)));
    }

    @Test
    public void containsVertex_positive() throws Exception {
        graph.insertVertex(left);
        assertThat(graph).as("Graph does not contain inserted vertex")
                .satisfies(graph -> assertTrue(graph.containsVertex(left)));
    }

    @Test
    public void containsVertex_negative() throws Exception {
        assertThat(graph).as("Graph does not contain inserted vertex")
                .satisfies(graph -> assertFalse(graph.containsVertex(left)));
    }

    @Test
    public void getEdgeFor_positive() throws Exception {
        graph.insertVertices(left, right);
        graph.createEdge(() -> testEdge);
    }

    @Test
    public void createAdjacencyList() throws Exception {
        Map<TestVertex, Set<TestVertex>> expectedAdjacencyList = createExpectedAdjacencyList();
        expectedAdjacencyList.keySet()
                .forEach(vertex -> graph.insertVertex(vertex));
        expectedAdjacencyList.entrySet()
                .forEach(
                        entry -> entry.getValue()
                                .forEach(
                                        adjacent -> graph.createEdge(() -> new TestEdge(entry.getKey(), adjacent))
                                )
                );
        Map<TestVertex, Set<TestVertex>> result = graph.createAdjacencyList();
        assertThat(expectedAdjacencyList).satisfies(expected -> {
            expected.entrySet().forEach(entry -> {
                Set<TestVertex> expectedSet = entry.getValue();
                Set<TestVertex> resultSet = result.get(entry.getKey());
                assertEquals(expectedSet.size(), resultSet.size());
                assertThat(expectedSet).satisfies(resultItem -> resultSet.contains(resultItem));
            });
        });
    }

    private Map<TestVertex, Set<TestVertex>> createExpectedAdjacencyList() {
        int numberOfVertices = 12;
        List<TestVertex> vertices = new ArrayList<>(numberOfVertices);
        Map<TestVertex, Set<TestVertex>> expectedAdjacencyList = new HashMap<>();
        IntStream.range(0, numberOfVertices).forEach(index -> {
            TestVertex testVertex = new TestVertex();
            vertices.add(index, testVertex);
            expectedAdjacencyList.put(testVertex, new HashSet<>());
        });
        int[][] indices = {
                {0, 1, 2},
                {1, 0, 2, 4},
                {2, 0, 1, 3},
                {3, 2, 6},
                {4, 1, 5, 6},
                {5, 4, 7},
                {6, 3, 4},
                {7, 5, 8},
                {8, 7, 9},
                {9, 8, 10},
                {10, 9, 11},
                {11, 10}
        };

        for (int i = 0; i < indices.length; i++) {
            Set<TestVertex> adjacentSet = expectedAdjacencyList.get(vertices.get(i));
            for (int j = 1; j < indices[i].length; j++) {
                adjacentSet.add(vertices.get(indices[i][j]));
            }
        }
        return expectedAdjacencyList;
    }


    static class TestVertex extends SimpleVertex<TestEdge> {

    }

    static class TestEdge extends SimpleEdge<TestVertex> {

        public TestEdge(TestVertex left, TestVertex right) {
            super(left, right);
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    private TestVertex vertex(UUID id) {
        TestVertex testVertex = mock(TestVertex.class);
        when(testVertex.id()).thenReturn(id);
        return testVertex;
    }

    private TestEdge edge(TestVertex left, TestVertex right) {
        TestEdge testEdge = mock(TestEdge.class);
        when(testEdge.left()).thenReturn(left);
        when(testEdge.right()).thenReturn(right);
        return testEdge;
    }

}