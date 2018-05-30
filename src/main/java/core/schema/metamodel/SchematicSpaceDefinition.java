package core.schema.metamodel;

public interface SchematicSpaceDefinition<GD extends GraphDefinition> extends SpaceDefinition {

    GD getGraphDefinition();
}
