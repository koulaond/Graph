package repository.schema.metamodel;

import java.util.UUID;

public class DefaultSchematicSpaceDefinition
        extends DefaultSpaceDefinition
        implements SchematicSpaceDefinition<DefaultGraphDefinition> {

    private DefaultGraphDefinition graphDefinition;

    public DefaultSchematicSpaceDefinition(UUID id, String name, DefaultGraphDefinition graphDefinition) {
        super(id, name);
        this.graphDefinition = graphDefinition;
    }

    @Override
    public DefaultGraphDefinition getGraphDefinition() {
        return graphDefinition;
    }

    public void setGraphDefinition(DefaultGraphDefinition graphDefinition) {
        this.graphDefinition = graphDefinition;
    }
}
