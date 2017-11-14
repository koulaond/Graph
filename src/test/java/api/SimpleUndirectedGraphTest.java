package api;

import api.undirected.SimpleUndirectedEdge;
import api.undirected.SimpleUndirectedGraph;
import api.undirected.SimpleUndirectedVertex;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class SimpleUndirectedGraphTest {

    private static final UUID LEFT_VERTEX_UUID = UUID.randomUUID();
    private static final UUID RIGHT_VERTEX_UUID = UUID.randomUUID();

    private TestUndirectedVertex left;

    private TestUndirectedVertex right;

    private TestUndirectedEdge testEdge;

    SimpleUndirectedGraph<TestUndirectedVertex, TestUndirectedEdge> graph;

    @Before
    public void setup() {
        left = vertex(LEFT_VERTEX_UUID);
        right = vertex(RIGHT_VERTEX_UUID);
        testEdge = edge(left, right);
        graph = new SimpleUndirectedGraph<>();
    }

    @Test
    public void createEdge() throws Exception {
        graph.insertVertices(left, right);
        graph.createEdge(() -> testEdge);

        assertThat(testEdge).satisfies(edge -> {
            assertTrue(graph.containsEdge(testEdge));
            assertThat(left).as("Left vertex does not contain an edge")
                    .satisfies(vertex -> verify(vertex).addEdge(testEdge));
            assertThat(right).as("Right vertex does not contain an edge")
                    .satisfies(vertex -> verify(vertex).addEdge(testEdge));
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
        Map<TestUndirectedVertex, Set<TestUndirectedVertex>> expectedAdjacencyList = createExpectedAdjacencyList();
        expectedAdjacencyList.keySet()
                .forEach(vertex -> graph.insertVertex(vertex));
        expectedAdjacencyList.entrySet()
                .forEach(
                        entry -> entry.getValue()
                                .forEach(
                                        adjacent -> graph.createEdge(() -> mockEdge(entry.getKey(), adjacent))
                                )
                );
        Map<TestUndirectedVertex, Set<TestUndirectedVertex>> result = graph.createAdjacencyList();
        assertThat(expectedAdjacencyList).satisfies(expected -> {
            expected.entrySet().forEach(entry -> {
                Set<TestUndirectedVertex> expectedSet = entry.getValue();
                Set<TestUndirectedVertex> resultSet = result.get(entry.getKey());
                assertEquals(expectedSet.size(), resultSet.size());
                assertThat(expectedSet).satisfies(resultItem -> resultSet.contains(resultItem));
            });
        });
    }

    private TestUndirectedEdge mockEdge(TestUndirectedVertex source, TestUndirectedVertex target) {
        TestUndirectedEdge edge = mock(TestUndirectedEdge.class);
        when(edge.getSourceVertex()).thenReturn(source);
        when(edge.getTargetVertex()).thenReturn(target);
        return edge;
    }

    private Map<TestUndirectedVertex, Set<TestUndirectedVertex>> createExpectedAdjacencyList() {
        int numberOfVertices = 12;
        List<TestUndirectedVertex> vertices = new ArrayList<>(numberOfVertices);
        Map<TestUndirectedVertex, Set<TestUndirectedVertex>> expectedAdjacencyList = new HashMap<>();
        IntStream.range(0, numberOfVertices).forEach(index -> {
            TestUndirectedVertex testVertex = new TestUndirectedVertex();
            vertices.add(index, testVertex);
            expectedAdjacencyList.put(testVertex, new HashSet<>());
        });
        int[][] indices = {
                {1, 2},
                {0, 2, 4},
                {0, 1, 3},
                {2, 6},
                {1, 5, 6},
                {4, 7},
                {3, 4},
                {5, 8},
                {7, 9},
                {8, 10},
                {9, 11},
                {10}
        };

        for (int i = 0; i < indices.length; i++) {
            Set<TestUndirectedVertex> adjacentSet = expectedAdjacencyList.get(vertices.get(i));
            for (int j = 0; j < indices[i].length; j++) {
                adjacentSet.add(vertices.get(indices[i][j]));
            }
        }
        return expectedAdjacencyList;
    }


    static class TestUndirectedVertex extends SimpleUndirectedVertex<TestUndirectedEdge> {

    }

    static class TestUndirectedEdge extends SimpleUndirectedEdge<TestUndirectedVertex> {

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    private TestUndirectedVertex vertex(UUID id) {
        TestUndirectedVertex testVertex = mock(TestUndirectedVertex.class);
        when(testVertex.getId()).thenReturn(id);
        return testVertex;
    }

    private TestUndirectedEdge edge(TestUndirectedVertex left, TestUndirectedVertex right) {
        TestUndirectedEdge testEdge = mock(TestUndirectedEdge.class);
        when(testEdge.getSourceVertex()).thenReturn(left);
        when(testEdge.getTargetVertex()).thenReturn(right);
        return testEdge;
    }

}