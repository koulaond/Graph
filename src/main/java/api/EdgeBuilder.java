package api;

import java.util.HashMap;
import java.util.Map;

public class EdgeBuilder {

    private String label;

    private Map<String, Object> properties = new HashMap<>();

    private Node sourceNode;

    private Node targetNode;

    public EdgeBuilder label(String label){
        this.label = label;
        return this;
    }

    public EdgeBuilder property(String key, Object value){
        this.properties.put(key, value);
        return this;
    }
    public EdgeBuilder sourceNode(Node sourceNode){
        this.sourceNode = sourceNode;
        return this;
    }

    public EdgeBuilder targetNode(Node targetNode){
        this.targetNode = targetNode;
        return this;
    }

    public Edge build(){
        DefaultEdge edge = new DefaultEdge(label, sourceNode, targetNode);
        edge.setProperties(properties);
        return edge;
    }
}
