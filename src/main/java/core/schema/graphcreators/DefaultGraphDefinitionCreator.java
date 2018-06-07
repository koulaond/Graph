package core.schema.graphcreators;

import model.definitions.GraphDefinition;

import java.util.Date;

public class DefaultGraphDefinitionCreator implements GraphDefinitionCreator<GraphDefinition> {

    @Override
    public GraphDefinition build() {
        GraphDefinition definition = new GraphDefinition();
        Date created = new Date();
        definition.setCreated(created);
        definition.setLastModified(created);
        return definition;
    }
}
