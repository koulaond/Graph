package core.schema.definitions;

public interface SchematicSpaceDefinition<GD extends GraphDefinition> extends SpaceDefinition {

    GD getGraphDefinition();
}
