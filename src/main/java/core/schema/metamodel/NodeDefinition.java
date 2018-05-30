package core.schema.metamodel;

public interface NodeDefinition<T, GD extends GraphDefinition> {
    GD getGraphDefinition();

    Class<T> getDescribedClass();

    String getNodeType();

    boolean isImmutable();

    Long getMaxCount();
}
