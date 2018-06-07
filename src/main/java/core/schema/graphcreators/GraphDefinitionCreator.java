package core.schema.graphcreators;

import model.definitions.GraphDefinition;

public interface GraphDefinitionCreator<GD extends GraphDefinition> {

    GD build();
}
