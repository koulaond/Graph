package core.schema.graphcreators;

import core.schema.definitions.GraphDefinition;

import java.util.Date;

public class DefaultGraphDefinitionCreator implements GraphDefinitionCreator<GraphDefinition> {

    @Override
    public GraphDefinition create() {
        GraphDefinition definition = new GraphDefinition();
        Date created = new Date();
        definition.setCreated(created);
        definition.setLastModified(created);
        return definition;
    }
}
