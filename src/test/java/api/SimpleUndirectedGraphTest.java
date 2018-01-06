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

    private SimpleUndirectedVertex leftVertex;

    private SimpleUndirectedVertex rightVertex;

    private SimpleUndirectedEdge edge;

    private SimpleUndirectedGraph graph;

    @Before
    public void setup() {
        leftVertex = vertex(LEFT_VERTEX_UUID);
        rightVertex = vertex(RIGHT_VERTEX_UUID);
        edge = edge(leftVertex, rightVertex);
        graph = new SimpleUndirectedGraph();
    }

    @Test
    public void createEdge() throws Exception {
        graph.insertVertices(leftVertex, rightVertex);
        graph.createEdge(() -> edge);
        assertThat(edge).satisfies(edge -> {
            assertTrue(graph.containsEdge(this.edge));
            assertThat(leftVertex).as("Left vertex does not contain an edge")
                    .satisfies(vertex -> verify(vertex).addEdge(this.edge));
            assertThat(rightVertex).as("Right vertex does not contain an edge")
                    .satisfies(vertex -> verify(vertex).addEdge(this.edge));
        });
    }

    @Test
    public void containsEdge_positive() throws Exception {
        graph.insertVertices(leftVertex, rightVertex);
        graph.createEdge(() -> edge);
        assertThat(graph).as("Graph does not contain particular edge")
                .satisfies(graph -> assertTrue(graph.containsEdge(edge)));
    }

    @Test
    public void containsEdge_negative() throws Exception {
        assertThat(graph).as("Graph SHOULD not contain particular edge")
                .satisfies(graph -> assertFalse(graph.containsEdge(edge)));
    }

    @Test
    public void containsEdgeForVertices_positive() throws Exception {
        graph.insertVertices(leftVertex, rightVertex);
        graph.createEdge(() -> edge);
        when(edge.isForVertices(leftVertex, rightVertex)).thenReturn(true);
        assertThat(graph).as("Graph does not contain particular edge for given vertices")
                .satisfies(graph -> assertTrue(graph.containsEdgeForVertices(leftVertex, rightVertex)));
    }

    @Test
    public void containsEdgeForVertices_negative() throws Exception {
        when(edge.isForVertices(leftVertex, rightVertex)).thenReturn(false);
        assertThat(graph).as("Graph SHOULD not contain particular edge for given vertices")
                .satisfies(graph -> assertFalse(graph.containsEdgeForVertices(leftVertex, rightVertex)));
    }

    @Test
    public void containsVertex_positive() throws Exception {
        graph.insertVertex(leftVertex);
        assertThat(graph).as("Graph does not contain inserted vertex")
                .satisfies(graph -> assertTrue(graph.containsVertex(leftVertex)));
    }

    @Test
    public void containsVertex_negative() throws Exception {
        assertThat(graph).as("Graph does not contain inserted vertex")
                .satisfies(graph -> assertFalse(graph.containsVertex(leftVertex)));
    }

    @Test
    public void getEdgeFor_positive() throws Exception {
        graph.insertVertices(leftVertex, rightVertex);
        graph.createEdge(() -> edge);
    }

    @Test
    public void createAdjacencyList() throws Exception {
        Map<SimpleUndirectedVertex, Set<SimpleUndirectedVertex>> expectedAdjacencyList = createExpectedAdjacencyList();
        expectedAdjacencyList.keySet()
                .forEach(vertex -> graph.insertVertex(vertex));
        expectedAdjacencyList.entrySet()
                .forEach(
                        entry -> entry.getValue()
                                .forEach(
                                        adjacent -> graph.createEdge(() -> mockEdge(entry.getKey(), adjacent))
                                )
                );
        Map<SimpleUndirectedVertex, Set<SimpleUndirectedVertex>> result = graph.createAdjacencyList();
        assertThat(expectedAdjacencyList).satisfies(expected -> {
            expected.entrySet().forEach(entry -> {
                Set<SimpleUndirectedVertex> expectedSet = entry.getValue();
                Set<SimpleUndirectedVertex> resultSet = result.get(entry.getKey());
                assertEquals(expectedSet.size(), resultSet.size());
                assertThat(expectedSet).satisfies(resultItem -> resultSet.contains(resultItem));
            });
        });
    }

    private SimpleUndirectedEdge mockEdge(SimpleUndirectedVertex source, SimpleUndirectedVertex target) {
        SimpleUndirectedEdge edge = mock(SimpleUndirectedEdge.class);
        when(edge.getSourceVertex()).thenReturn(source);
        when(edge.getTargetVertex()).thenReturn(target);
        return edge;
    }

    private Map<SimpleUndirectedVertex, Set<SimpleUndirectedVertex>> createExpectedAdjacencyList() {
        int numberOfVertices = 12;
        List<SimpleUndirectedVertex> vertices = new ArrayList<>(numberOfVertices);
        Map<SimpleUndirectedVertex, Set<SimpleUndirectedVertex>> expectedAdjacencyList = new HashMap<>();
        IntStream.range(0, numberOfVertices).forEach(index -> {
            SimpleUndirectedVertex testVertex = new SimpleUndirectedVertex(new Date().toString(), new HashMap<>());
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
            Set<SimpleUndirectedVertex> adjacentSet = expectedAdjacencyList.get(vertices.get(i));
            for (int j = 0; j < indices[i].length; j++) {
                adjacentSet.add(vertices.get(indices[i][j]));
            }
        }
        return expectedAdjacencyList;
    }


  

    private SimpleUndirectedVertex vertex(UUID id) {
        SimpleUndirectedVertex testVertex = mock(SimpleUndirectedVertex.class);
        when(testVertex.getId()).thenReturn(id);
        return testVertex;
    }

    private SimpleUndirectedEdge edge(SimpleUndirectedVertex left, SimpleUndirectedVertex right) {
        SimpleUndirectedEdge testEdge = mock(SimpleUndirectedEdge.class);
        when(testEdge.getSourceVertex()).thenReturn(left);
        when(testEdge.getTargetVertex()).thenReturn(right);
        return testEdge;
    }

}