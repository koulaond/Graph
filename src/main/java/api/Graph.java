package api;

import java.util.Map;
import java.util.Set;

public interface Graph<N extends Node> extends Node {

    N getInitialNode();

    Set<N> getSubNodes();

    Map<Edge, N> getInputEdges();

    Map<N, Edge> getOutputEdges();

}
