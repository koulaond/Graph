package repository.schema.graphcreators;

import repository.schema.metamodel.GraphDefinition;
import repository.schema.metamodel.SchematicSpaceDefinition;

public interface GraphDefinitionCreator<GD extends GraphDefinition, SD extends SchematicSpaceDefinition> {

    GD build(SD schemaDefinition);
}
