package core.schema.graphcreators;

import core.schema.metamodel.GraphDefinition;

public interface GraphDefinitionCreator<GD extends GraphDefinition> {

    GD build();
}
