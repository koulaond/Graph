package core.repository;

import model.Node;
import model.Relation;

import java.util.UUID;

public interface Repository {

    Node createNode(Node node);

    Relation createRelation(Relation relation);

    Node updateNode(Node node);

    Relation updateRelation(Relation relation);

    Node findNode(UUID id);

    Relation findRelation(UUID uuid);

    Node deleteNode(Node node);

    Relation deleteRelation(Relation relation);
}
