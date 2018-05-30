package core.schema.metamodel;

import core.schema.descriptions.PropertyDescription;
import core.schema.descriptions.RelationshipDescription;

import java.util.Set;

/**
 * Special Node class that describes @{@link model.Node}s of the given type.
 *
 * @param <T> described class
 */
public class DefaultNodeDefinition<T> implements NodeDefinition<T, DefaultGraphDefinition> {

    /**
     * Parent Graph definition.
     */
    private DefaultGraphDefinition graphDefinition;

    /**
     * Model class that is described by this meta-node.
     */
    private Class<T> describedClass;

    /**
     * Unique type for nodes that are defined by this meta-node. It is something like class type for nodes.
     */
    private String nodeType;

    /**
     * Indicator whether nodes defined by this meta-node are immutable.
     */
    private boolean immutable;

    /**
     * Maximum count of nodes defined by this meta-node in the core.
     */
    private Long maxCount;

    /**
     * Set of property description that describe properties, which can be stored in the node of this type.
     */
    private Set<PropertyDescription> propertyDescriptions;

    private Set<RelationshipDescription> relationshipDescriptions;

    @Override
    public DefaultGraphDefinition getGraphDefinition() {
        return graphDefinition;
    }

    public void setGraphDefinition(DefaultGraphDefinition graphDefinition) {
        this.graphDefinition = graphDefinition;
    }

    @Override
    public Class<T> getDescribedClass() {
        return describedClass;
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public boolean isImmutable() {
        return immutable;
    }

    @Override
    public Long getMaxCount() {
        return maxCount;
    }

    public Set<PropertyDescription> getPropertyDescriptions() {
        return propertyDescriptions;
    }

    public void setDescribedClass(Class<T> describedClass) {
        this.describedClass = describedClass;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setImmutable(boolean immutable) {
        this.immutable = immutable;
    }

    public void setMaxCount(Long maxCount) {
        this.maxCount = maxCount;
    }

    public void setPropertyDescriptions(Set<PropertyDescription> propertyDescriptions) {
        this.propertyDescriptions = propertyDescriptions;
    }

    public Set<RelationshipDescription> getRelationshipDescriptions() {
        return relationshipDescriptions;
    }

    public void setRelationshipDescriptions(Set<RelationshipDescription> relationshipDescriptions) {
        this.relationshipDescriptions = relationshipDescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultNodeDefinition<?> defaultNodeDefinition = (DefaultNodeDefinition<?>) o;

        return nodeType != null ? nodeType.equals(defaultNodeDefinition.nodeType) : defaultNodeDefinition.nodeType == null;
    }

    @Override
    public int hashCode() {
        return nodeType != null ? nodeType.hashCode() : 0;
    }
}
