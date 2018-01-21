package api;

import lombok.NonNull;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class GraphHandler {

    @NonNull
    private DefaultGraph<DefaultNode> graph;

    public Graph getGraph() {
        return graph;
    }

    public UUID addNode(String label, Map<String, Object> properties) {
        DefaultNode node = Builders.nodeBuilder()
                .label(label)
                .properties(properties)
                .parentGraph(graph)
                .build();
        this.graph.addSubNode(node);
        return node.getUuid();
    }

    public UUID connectNodes(UUID sourceNodeUUID,
                             UUID targetNodeUUID,
                             String label,
                             Map<String, Object> properties) {

        DefaultNode sourceNode = graph.getSubNode(sourceNodeUUID);
        DefaultNode targetNode = graph.getSubNode(targetNodeUUID);
        if (sourceNode == null || targetNode == null) {
            throw new IllegalStateException("Some of the connecting nodes does not exist.");
        }
        DefaultConnection connection = Builders.connectionBuilder()
                .label(label)
                .properties(properties)
                .sourceNode(sourceNode)
                .targetNode(targetNode)
                .build();
        graph.addInnerConnection(connection);
        sourceNode.addOutputConnection(connection);
        targetNode.addInputConnection(connection);
        return connection.getUuid();
    }

    public Set<Connection> getConnectionsBetween(UUID sourceNodeUUID, UUID targetNodeUUID) {
        return graph.getInnerConnections()
                .entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .filter(connection ->
                        connection.getSourceNode().getUuid().equals(sourceNodeUUID)
                                && connection.getTargetNode().getUuid().equals(targetNodeUUID)
                )
                .collect(Collectors.toSet());
    }

    public boolean removeConnection(UUID connUUID) {
        if(graph.containsInnerConnection(connUUID)){
            graph.removeInnerConnection(connUUID);
            graph.getSubNodes()
                    .stream()
                    .filter(subNode -> subNode.containsConnection(connUUID))
            .forEach(subNode -> subNode.removeConnection(connUUID));
            return true;
        }
        return false;
    }
}
