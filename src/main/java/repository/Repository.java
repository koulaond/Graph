package repository;

import model.Connection;
import model.Node;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface Repository {

    Optional<Node> findNode(UUID uuid);

    Collection<Node> searchNodes(Query<Node> query);

    Optional<Node> createNode(Node node);

    Optional<Connection> connectNodes(Node startNode, Node endNode, Map<String, Object> connectionProperties);

    Optional<Connection> findConnection(UUID uuid);

    Collection<Connection> searchConnections(Query<Connection> query);

}
