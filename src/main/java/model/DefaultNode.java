package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DefaultNode<R extends Relation> extends AbstractEntity implements Node<R> {

    @Getter
    @NonNull
    protected Graph parentGraph;

    protected Map<UUID, R> inputRelations;

    protected Map<UUID, R> outputRelations;

    protected DefaultNode(@NonNull Graph parentGraph) {
        this.parentGraph = parentGraph;
        this.inputRelations = new HashMap<>();
        this.outputRelations = new HashMap<>();
    }

    @Override
    public boolean includes(Node other) {
        return this.equals(other);
    }

    @Override
    public boolean isIncludedIn(@NonNull Graph graph) {
        return graph.containsNode(this);
    }

    void addInputRelation(R inputConnection){
        this.inputRelations.put(inputConnection.getUuid(), inputConnection);
    }

    void addOutputRelation(R outputConnection){
        this.outputRelations.put(outputConnection.getUuid(), outputConnection);
    }

    void removeInputRelation(UUID connUUID){
        this.inputRelations.remove(connUUID);
    }

    void removeOutputRelation(UUID connUUID){
        this.outputRelations.remove(connUUID);
    }

    void removeRelation(UUID connUUID){
        removeInputRelation(connUUID);
        removeOutputRelation(connUUID);
    }

    boolean containsInputConnection(UUID connUUID){
        return this.inputRelations.containsKey(connUUID);
    }

    boolean containsOutputConnection(UUID connUUID){
        return this.outputRelations.containsKey(connUUID);
    }

    boolean containsConnection(UUID connUUID){
        return containsInputConnection(connUUID) || containsOutputConnection(connUUID);
    }

    boolean containsConnection(Relation relation){
        return containsConnection(relation.getUuid());
    }
}
