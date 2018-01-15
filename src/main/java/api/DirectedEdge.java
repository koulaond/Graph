package api;

import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Getter
public class DirectedEdge extends AbstractEdge {

    @NonNull
    private Direction direction;

    public DirectedEdge(String label, Map<String, Object> properties, Node leftNode, Node rightNode) {
        this(label, properties, leftNode, rightNode, Direction.FORWARD);
    }

    public DirectedEdge(
            String label,
            Map<String, Object> properties,
            Node leftNode,
            Node rightNode,
            Direction direction) {
        super(label, properties, leftNode, rightNode);
        this.direction = direction;
    }

    public enum Direction {
        FORWARD,
        BACKWARD
    }
}