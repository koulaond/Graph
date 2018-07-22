package core.schema.graphcreators;

import core.schema.definitions.GraphDefinition;

public interface GraphDefinitionCreator<GD extends GraphDefinition> {

    GD create();
}
