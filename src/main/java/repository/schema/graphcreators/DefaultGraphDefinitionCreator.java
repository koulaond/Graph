package repository.schema.graphcreators;

import repository.schema.metamodel.DefaultGraphDefinition;
import repository.schema.metamodel.DefaultSchematicSpaceDefinition;

import java.util.Date;

public class DefaultGraphDefinitionCreator
        implements GraphDefinitionCreator<DefaultGraphDefinition, DefaultSchematicSpaceDefinition> {

    @Override
    public DefaultGraphDefinition build(DefaultSchematicSpaceDefinition schemaDefinition) {
        DefaultGraphDefinition definition = new DefaultGraphDefinition();
        definition.setSchemaDefinition(schemaDefinition);
        Date created = new Date();
        definition.setCreated(created);
        definition.setLastModified(created);
        return definition;
    }
}
