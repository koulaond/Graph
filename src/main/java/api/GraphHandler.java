package api;

import lombok.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class GraphHandler {

    @NonNull
    private DefaultGraph<DefaultNode, DefaultConnection> graph;

    public Graph getGraph() {
        return graph;
    }

    public Node addNode(String label, Map<String, Object> properties) {
        NodeBuilder nodeBuilder = Builders.nodeBuilder()
                .label(label)
                .parentGraph(graph);
        if (properties != null) {
            nodeBuilder.properties(properties);
        }
        DefaultNode node = nodeBuilder.build();
        this.graph.addSubNode(node);
        return node;
    }

    public Node addNode(String label) {
        return addNode(label, null);
    }

    public Connection connectNodes(@NonNull Node sourceNode,
                                   @NonNull Node targetNode,
                                   @NonNull String connectionLabel) {
        return connectNodes(sourceNode.getUuid(), targetNode.getUuid(), connectionLabel);
    }

    public Connection connectNodes(@NonNull Node sourceNode,
                                   @NonNull Node targetNode,
                                   @NonNull String connectionLabel,
                                   @NonNull Map<String, Object> properties) {
        return connectNodes(sourceNode.getUuid(), targetNode.getUuid(), connectionLabel, properties);
    }

    public Connection connectNodes(@NonNull UUID sourceNodeUUID,
                                   @NonNull UUID targetNodeUUID,
                                   @NonNull String connectionLabel) {
        return connectNodes(sourceNodeUUID, targetNodeUUID, connectionLabel, null);
    }

    public Connection connectNodes(@NonNull UUID sourceNodeUUID,
                                   @NonNull UUID targetNodeUUID,
                                   @NonNull String connectionLabel,
                                   Map<String, Object> properties) {

        DefaultNode sourceNode = graph.getSubNode(sourceNodeUUID);
        DefaultNode targetNode = graph.getSubNode(targetNodeUUID);
        if (sourceNode == null || targetNode == null) {
            throw new IllegalStateException("Some of the connecting nodes does not exist.");
        }
        ConnectionBuilder connectionBuilder = Builders.connectionBuilder()
                .label(connectionLabel)
                .sourceNode(sourceNode)
                .targetNode(targetNode);
        if (properties != null) {
            connectionBuilder.properties(properties);
        }
        DefaultConnection connection = connectionBuilder.build();
        graph.addInnerConnection(connection);
        sourceNode.addOutputConnection(connection);
        targetNode.addInputConnection(connection);
        return connection;
    }

    public Set<Node> getNodesByProperty(String key) {
        return graph.getSubNodes().stream()
                .filter(node -> node.hasProperty(key))
                .collect(toSet());
    }

    public Set<Node> getNodesByProperty(String key, Object value) {
        return graph.getSubNodes().stream()
                .filter(node -> node.hasProperty(key, value))
                .collect(toSet());
    }

    public Set<Node> getNodesByLabel(String label){
        return graph.getSubNodes().stream()
                .filter(node -> Objects.equals(node.getLabel(), label))
                .collect(toSet());
    }

    public Set<Connection> getInnerConnections(){
        return new HashSet<>(graph.getInnerConnections().values());
    }

    public Set<Connection> getInnerConnectionsByProperty(String key){
        return graph.getInnerConnections().values().stream()
                .filter(connection -> connection.hasProperty(key))
                .collect(toSet());

    }

    public Set<Connection> getInnerConnectionsByProperty(String key, Object value){
        return graph.getInnerConnections().values().stream()
                .filter(connection -> connection.hasProperty(key, value))
                .collect(toSet());
    }

    public  Set<Connection> getInputConnectionsByProperty(String key) {
        return graph.getInputConnections().stream()
                .filter(connection -> connection.hasProperty(key))
                .collect(toSet());
    }

    public  Set<Connection> getInputConnectionsByProperty(String key, Object value) {
        return graph.getInputConnections().stream()
                .filter(connection -> connection.hasProperty(key, value))
                .collect(toSet());
    }

    public  Set<Connection> getOutputConnectionsByProperty(String key) {
        return graph.getOutputConnections().stream()
                .filter(connection -> connection.hasProperty(key))
                .collect(toSet());
    }

    public  Set<Connection> getOutputConnectionsByProperty(String key, Object value) {
        return graph.getOutputConnections().stream()
                .filter(connection -> connection.hasProperty(key, value))
                .collect(toSet());
    }

    public Set<Connection> getConnectionsBetween(@NonNull Node sourceNode, @NonNull Node targetNode) {
        return getConnectionsBetween(sourceNode.getUuid(), targetNode.getUuid());
    }

    public Set<Connection> getConnectionsBetween(@NonNull UUID sourceNodeUUID, @NonNull UUID targetNodeUUID) {
        return graph.getInnerConnections()
                .entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .filter(connection ->
                        connection.getSourceNode().getUuid().equals(sourceNodeUUID)
                                && connection.getTargetNode().getUuid().equals(targetNodeUUID)
                )
                .collect(toSet());
    }

    public boolean removeConnection(@NonNull Connection connection) {
        return removeConnection(connection.getUuid());
    }

    public boolean removeConnection(@NonNull UUID connUUID) {
        if (graph.containsInnerConnection(connUUID)) {
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
