package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Edge extends AbstractGraphElement {

    @NonNull
    private Vertex v1;

    @NonNull
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

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (!(that instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) that;
        return this.getV1().equals(edge.getV1())
                && this.getV2().equals(edge.getV2());
    }
}
