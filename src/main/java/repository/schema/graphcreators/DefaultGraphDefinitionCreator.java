package repository.schema.graphcreators;

import repository.schema.metamodel.DefaultGraphDefinition;

import java.util.Date;

public class DefaultGraphDefinitionCreator implements GraphDefinitionCreator<DefaultGraphDefinition> {

    @Override
    public DefaultGraphDefinition build(String schema) {
        DefaultGraphDefinition definition = new DefaultGraphDefinition();
        definition.setSchema(schema);
        Date created = new Date();
        definition.setCreated(created);
        definition.setLastModified(created);
        return definition;
    }
}
