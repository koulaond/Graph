package api;

import java.util.HashMap;
import java.util.Map;

class NodeBuilder {

    protected String label;

    protected Map<String, Object> properties = new HashMap<>();

    protected Graph parentGraph;

    public NodeBuilder label(String label){
        this.label = label;
        return this;
    }

    public NodeBuilder property(String key, Object value){
        this.properties.put(key, value);
        return this;
    }

    public NodeBuilder parentGraph(Graph parentGraph){
        this.parentGraph = parentGraph;
        return this;
    }

    public Node build(){
        DefaultNode node = new DefaultNode(label, parentGraph);
        node.setProperties(properties);
        return node;
    }
}
