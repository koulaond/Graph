package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DefaultNode<C extends Relation> extends AbstractEntity implements Node<C> {

    @Getter
    @NonNull
    protected Graph parentGraph;

    protected Map<UUID, C> inputConnections;

    protected Map<UUID, C> outputConnections;

    protected DefaultNode(@NonNull Graph parentGraph) {
        this.parentGraph = parentGraph;
        this.inputConnections = new HashMap<>();
        this.outputConnections = new HashMap<>();
    }

    @Override
    public boolean includes(Node other) {
        return this.equals(other);
    }

    @Override
    public boolean isIncludedIn(@NonNull Graph graph) {
        return graph.containsNode(this);
    }

    void addInputConnection(C inputConnection){
        this.inputConnections.put(inputConnection.getUuid(), inputConnection);
    }

    void addOutputConnection(C outputConnection){
        this.outputConnections.put(outputConnection.getUuid(), outputConnection);
    }

    void removeInputConnection(UUID connUUID){
        this.inputConnections.remove(connUUID);
    }

    void removeOutputConnection(UUID connUUID){
        this.outputConnections.remove(connUUID);
    }

    void removeConnection(UUID connUUID){
        removeInputConnection(connUUID);
        removeOutputConnection(connUUID);
    }

    boolean containsInputConnection(UUID connUUID){
        return this.inputConnections.containsKey(connUUID);
    }

    boolean containsOutputConnection(UUID connUUID){
        return this.outputConnections.containsKey(connUUID);
    }

    boolean containsConnection(UUID connUUID){
        return containsInputConnection(connUUID) || containsOutputConnection(connUUID);
    }

    boolean containsConnection(Relation relation){
        return containsConnection(relation.getUuid());
    }
}
