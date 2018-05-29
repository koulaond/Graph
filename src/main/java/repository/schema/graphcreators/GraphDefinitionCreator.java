package repository.schema.graphcreators;

import repository.schema.metamodel.GraphDefinition;

public interface GraphDefinitionCreator<GD extends GraphDefinition> {

    GD build(String schema);
}
