package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public abstract class AbstractEdge extends AbstractItem implements Edge {

    @NonNull
    private Node leftNode;

    @NonNull
    private Node rightNode;

    public AbstractEdge(String label, Map<String, Object> properties, Node leftNode, Node rightNode) {
        super(label, properties);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (!(that instanceof AbstractEdge)) {
            return false;
        }
        AbstractEdge edge = (AbstractEdge) that;
        return this.getLeftNode().equals(this.getLeftNode())
                && this.getRightNode().equals(this.getRightNode());
    }
}
