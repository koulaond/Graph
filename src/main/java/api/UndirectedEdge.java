package api;

import java.util.Map;

public class UndirectedEdge extends AbstractEdge implements Edge {

    public UndirectedEdge(String label, Map<String, Object> properties, Node leftNode, Node rightNode) {
        super(label, properties, leftNode, rightNode);
    }

    @Override
    public boolean equals(Object that){
        return that instanceof UndirectedEdge;
    }
}
