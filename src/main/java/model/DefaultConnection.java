package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
class DefaultConnection extends AbstractItem implements Connection {

    @NonNull
    private Node sourceNode;

    @NonNull
    private Node targetNode;

    public DefaultConnection(String label, Node sourceNode, Node targetNode) {
        super(label);
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (!(that instanceof DefaultConnection)) {
            return false;
        }
        DefaultConnection connection = (DefaultConnection) that;
        return this.getSourceNode().equals(connection.getSourceNode())
                && this.getTargetNode().equals(connection.getTargetNode());
    }
}
