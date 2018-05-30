package model;

import java.util.Map;
import java.util.UUID;

public class Node extends GraphEntity {

    private Map<Relation, Direction> relations;

    public Node(UUID id,
                Map<String, Object> properties,
                Graph parentGraph,
                Map<Relation, Direction> relations) {

        super(id, properties, parentGraph);
        this.relations = relations;
    }

    public Map<Relation, Direction> getRelations() {
        return relations;
    }

}
