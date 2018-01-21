package api;

import java.util.*;

public class GraphBuilder extends NodeBuilder {

    private Node initialNode;

    private Map<UUID, Node> subNodes = new HashMap<>();

    private Set<Connection> innerConnections = new HashSet<>();

    @Override
    public GraphBuilder label(String label) {
        return (GraphBuilder) super.label(label);
    }

    @Override
    public GraphBuilder property(String key, Object value) {
        return (GraphBuilder) super.property(key, value);
    }

    @Override
    public GraphBuilder parentGraph(Graph parentGraph) {
        return (GraphBuilder) super.parentGraph(parentGraph);
    }

    public GraphBuilder initialNode(Node initialNode){
        if(!subNodes.containsKey(initialNode.getUuid())){
            throw new IllegalStateException("Node must be added as a sub-node in this graph.");
        }
        this.initialNode = initialNode;
        return this;
    }

    public GraphBuilder subNode(Node subNode, boolean initial){
        this.subNodes.put(subNode.getUuid(), subNode);
        if(initial){
            this.initialNode = subNode;
        }
        return this;
    }

    public GraphBuilder subNode(Node subNode){
        return subNode(subNode, false);
    }

    public GraphBuilder innerConnection(Connection innerConnection){
        this.innerConnections.add(innerConnection);
        return this;
    }

    @Override
    public DefaultGraph build() {
        DefaultGraph graph = new DefaultGraph(label, initialNode, parentGraph);
        graph.setProperties(properties);
        graph.setSubNodes(subNodes);
        return graph;
    }
}
