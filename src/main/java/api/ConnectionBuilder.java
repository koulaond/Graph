package api;

import java.util.HashMap;
import java.util.Map;

public class ConnectionBuilder {

    private String label;

    private Map<String, Object> properties = new HashMap<>();

    private Node sourceNode;

    private Node targetNode;

    public ConnectionBuilder label(String label){
        this.label = label;
        return this;
    }

    public ConnectionBuilder property(String key, Object value){
        this.properties.put(key, value);
        return this;
    }

    public ConnectionBuilder properties(Map<String, Object> properties){
        this.properties.putAll(properties);
        return this;
    }
    public ConnectionBuilder sourceNode(Node sourceNode){
        this.sourceNode = sourceNode;
        return this;
    }

    public ConnectionBuilder targetNode(Node targetNode){
        this.targetNode = targetNode;
        return this;
    }

    public DefaultConnection build(){
        DefaultConnection edge = new DefaultConnection(label, sourceNode, targetNode);
        edge.setProperties(properties);
        return edge;
    }
}
