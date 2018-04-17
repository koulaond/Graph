package model;

import java.util.Set;

public interface Graph<N extends Node, R extends Relation> extends GraphElement {

    N getInitialNode();

    Set<R> getRelations();

    Set<N> getNodes();

    boolean containsNode(N node);

    boolean containsRelation(R relation);
}
