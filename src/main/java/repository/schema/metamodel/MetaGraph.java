package repository.schema.metamodel;

import model.DefaultGraph;
import model.Node;

public class MetaGraph extends DefaultGraph {

    private String schema;

    public MetaGraph(Node initialNode, String schema) {
        super(initialNode);
        this.schema = schema;
    }

    public String getSchema() {
        return schema;
    }
}
