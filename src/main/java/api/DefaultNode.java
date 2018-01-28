package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toSet;

@Getter
@Setter
public class DefaultNode<C extends Connection> extends AbstractItem implements Node<C> {

    @Getter
    protected Graph parentGraph;

    protected Map<UUID, C> inputConnections;

    protected Map<UUID, C> outputConnections;

    protected DefaultNode(@NonNull String label,
                          @NonNull Graph parentGraph) {
        super(label);
        this.parentGraph = parentGraph;
        this.inputConnections = new HashMap<>();
        this.outputConnections = new HashMap<>();
    }

    @Override
    public Set<C> getInputConnections() {
        return unmodifiableSet(inputConnections.entrySet().stream().map(entry -> entry.getValue()).collect(toSet()));
    }

    @Override
    public Set<C> getOutputConnections() {
        return unmodifiableSet(outputConnections.entrySet().stream().map(entry -> entry.getValue()).collect(toSet()));
    }

    @Override
    public boolean includes(Node other) {
        return this.equals(other);
    }

    @Override
    public boolean isIncludedIn(@NonNull Graph graph) {
        return graph.includes(this);
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

    boolean containsConnection(Connection connection){
        return containsConnection(connection.getUuid());
    }

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (that instanceof DefaultNode) {
            return true;
        }
        return false;
    }
}
