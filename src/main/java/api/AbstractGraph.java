package api;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Getter
public class AbstractGraph<E extends Edge> extends AbstractNode implements Graph {

    private AbstractVertex initialVertex;

    private Set<Node> subNodes = new HashSet<>();

    private Set<E> insideEdges = new HashSet<>();

    private Set<E> outsideEdges = new HashSet<>();

    protected AbstractGraph(String label, Map<String, Object> properties, Graph parentGraph) {
        super(label, properties, parentGraph);
    }

    @Override
    public boolean includes(Node other) {
        return subNodes.stream()
                .anyMatch(node -> node.equals(other));
    }
}
