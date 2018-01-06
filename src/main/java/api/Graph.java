package api;

import lombok.Setter;

import java.util.*;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

@Setter
public class Graph {
    private Set<Vertex> vertices;
    private Map<Vertex, Set<Edge>> adjacencyList;

    public Graph() {
        this.vertices = new HashSet<>();
        this.adjacencyList = new HashMap<>();
    }

    public Set<Vertex> getVertices() {
        return unmodifiableSet(vertices);
    }

    public Map<Vertex, Set<Edge>> getAdjacencyList() {
        return unmodifiableMap(adjacencyList);
    }

    public void addVertex(Vertex vertex) {
        validateVerticesAreNotPresent(vertex);
        this.vertices.add(vertex);
    }

    public void insertEdge(Vertex from, Vertex to, Edge edge) {
        validateVerticesArePresent(from, to);
        updateAdjacencyList(from, edge);
        updateAdjacencyList(to, edge);
    }

    private void updateAdjacencyList(Vertex vertex, Edge edge) {
        if (adjacencyList.containsKey(vertex)) {
            Set<Edge> edges = adjacencyList.get(vertex);
            if (edges == null) {
                edges = new HashSet<>();
            }
            edges.add(edge);
        } else {
            Set<Edge> edges = new HashSet<>();
            edges.add(edge);
            adjacencyList.put(vertex, edges);
        }
    }

    private void validateVerticesArePresent(Vertex... vertices) {
        for (Vertex vertex : vertices) {
            if (!this.vertices.contains(vertex)) {
                throw new IllegalStateException(String.format("Vertex %s is NOT present in graph.", vertex.toString()));
            }
        }
    }

    private void validateVerticesAreNotPresent(Vertex... vertices) {
        for (Vertex vertex : vertices) {
            if (this.vertices.contains(vertex)) {
                throw new IllegalStateException(String.format("Vertex %s is already present in graph.", vertex.toString()));
            }
        }
    }
}
