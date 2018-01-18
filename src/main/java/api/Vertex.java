package api;

import java.util.Set;

public interface Vertex extends Node {

   Set<Edge> getInputEdges();

   Set<Edge> getOutputEdges();
}
