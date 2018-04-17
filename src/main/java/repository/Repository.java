package repository;

import model.Relation;
import model.Node;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface Repository {

    Optional<Node> findNode(UUID uuid);

    Collection<Node> searchNodes(Query<Node> query);

    Optional<Node> createNode(Node node);

    Optional<Relation> connectNodes(Node startNode, Node endNode, Map<String, Object> connectionProperties);

    Optional<Relation> findRelationship(UUID uuid);

    Collection<Relation> searchRelationships(Query<Relation> query);

}
