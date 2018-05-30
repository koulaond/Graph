package core.schema.graphcreators;

import core.schema.metamodel.GraphDefinition;
import core.schema.metamodel.SchematicSpaceDefinition;

public interface GraphDefinitionCreator<GD extends GraphDefinition, SD extends SchematicSpaceDefinition> {

    GD build(SD schemaDefinition);
}
