package api;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Edge extends AbstractGraphElement {
    private Vertex v1;
    private Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(String label, Map<String, Object> properties, Vertex v1, Vertex v2) {
        super(label, properties);
        this.v1 = v1;
        this.v2 = v2;
    }
}
