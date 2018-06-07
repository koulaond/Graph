package core.schema.graphcreators;

public interface GraphDefinitionCreator<GD extends GraphDefinition> {

    GD build();
}
