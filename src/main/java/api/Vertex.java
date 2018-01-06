package api;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Vertex extends AbstractGraphElement {
    private int weight;

    public Vertex(int weight) {
        this.weight = weight;
    }

    public Vertex(String label, Map<String, Object> properties, int weight) {
        super(label, properties);
        this.weight = weight;
    }
}
