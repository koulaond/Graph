package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public abstract class AbstractGraph<E extends Edge> extends AbstractItem implements Node{

    private int weight;

    private Vertex initialVertex;

    private Set<Node> nodes = new HashSet<>();

    private Set<E> edges = new HashSet<>();

    protected AbstractGraph(String label, Map<String, Object> properties) {
        super(label, properties);
    }
}
