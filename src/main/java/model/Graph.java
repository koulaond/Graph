package model;

import java.util.Set;

public interface Graph extends GraphElement {

    Node getInitialNode();

    Set<Connection> getConnections();

    Set<Node> getNodes();

    boolean containsNode(Node node);

    boolean containsConnection(Connection connection);
}
