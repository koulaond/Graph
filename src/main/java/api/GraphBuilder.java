package api;

import java.util.HashSet;
import java.util.Set;

class GraphBuilder extends NodeBuilder {

    private Node initialNode;

    private Set<Node> subNodes = new HashSet<>();

    public GraphBuilder initialNode(Node initialNode){
        if(!subNodes.contains(initialNode)){
            throw new IllegalStateException("Node must be added as a sub-node in this graph.");
        }
        this.initialNode = initialNode;
        return this;
    }

    public GraphBuilder subNode(Node subNode, boolean initial){
        this.subNodes.add(subNode);
        if(initial){
            this.initialNode = subNode;
        }
        return this;
    }

    public GraphBuilder subnode(Node subNode){
        return subNode(subNode, false);
    }

    @Override
    public Graph build() {
        DefaultGraph graph = new DefaultGraph(label, initialNode, parentGraph);
        graph.setProperties(properties);
        graph.setSubNodes(subNodes);
        return graph;
    }
}
