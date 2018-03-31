package model;

import java.util.Set;

public interface Graph extends GraphElement {

    Node getInitialNode();

    Set<Relationship> getConnections();

    Set<Node> getNodes();

    boolean containsNode(Node node);

    boolean containsConnection(Relationship relationship);
}
