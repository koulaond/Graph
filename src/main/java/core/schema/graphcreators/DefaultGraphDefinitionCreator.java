package core.schema.graphcreators;

import core.schema.metamodel.DefaultGraphDefinition;

import java.util.Date;

public class DefaultGraphDefinitionCreator implements GraphDefinitionCreator<DefaultGraphDefinition> {

    @Override
    public DefaultGraphDefinition build() {
        DefaultGraphDefinition definition = new DefaultGraphDefinition();
        Date created = new Date();
        definition.setCreated(created);
        definition.setLastModified(created);
        return definition;
    }
}
