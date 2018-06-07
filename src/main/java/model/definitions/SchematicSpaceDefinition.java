package model.definitions;

import java.util.UUID;

public class SchematicSpaceDefinition extends SpaceDefinition {

    private GraphDefinition graphDefinition;

    public SchematicSpaceDefinition(UUID id, String name, GraphDefinition graphDefinition) {
        super(id, name);
        this.graphDefinition = graphDefinition;
    }

    public GraphDefinition getGraphDefinition() {
        return graphDefinition;
    }

    public void setGraphDefinition(GraphDefinition graphDefinition) {
        this.graphDefinition = graphDefinition;
    }
}
