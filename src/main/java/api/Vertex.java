package api;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Vertex extends AbstractItem implements Node{
    private int weight;

    public Vertex(String label, Map<String, Object> properties, int weight) {
        super(label, properties);
        this.weight = weight;
    }

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (!(that instanceof Vertex)) {
            return false;
        }
        return ((Vertex) that).weight == this.weight;
    }
}
