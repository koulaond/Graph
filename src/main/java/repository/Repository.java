package repository;

import model.Connection;
import model.Node;

import java.util.UUID;

public interface Repository {

    Match<Node> findNode(UUID uuid);

    CollectionMatch<Node> searchNodes(Query<Node> query);

    Match<Connection> findConnection(UUID uuid);

    CollectionMatch<Connection> searchConnections(Query<Connection> query);

}
