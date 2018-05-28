package repository.schema.metamodel;

import repository.schema.descriptions.PropertyDescription;
import repository.schema.descriptions.RelationshipDescription;

import java.util.Set;

/**
 * Special Node class that describes @{@link model.Node}s of the given type.
 * @param <T> described class
 */
public class MetaNode<T>{

    private MetaGraph metaGraph;

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
     * Maximum count of nodes defined by this meta-node in the repository.
     */
    private Long maxCount;

    /**
     * Set of property description that describe properties, which can be stored in the node of this type.
     */
    private Set<PropertyDescription> propertyDescriptions;


    private Set<RelationshipDescription> relationshipDescriptions;

    public MetaGraph getMetaGraph() {
        return metaGraph;
    }

    public Class<T> getDescribedClass() {
        return describedClass;
    }

    public String getNodeType() {
        return nodeType;
    }

    public boolean isImmutable() {
        return immutable;
    }

    public Long getMaxCount() {
        return maxCount;
    }

    public Set<PropertyDescription> getPropertyDescriptions() {
        return propertyDescriptions;
    }

    public void setMetaGraph(MetaGraph metaGraph) {
        this.metaGraph = metaGraph;
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

        MetaNode<?> metaNode = (MetaNode<?>) o;

        return nodeType != null ? nodeType.equals(metaNode.nodeType) : metaNode.nodeType == null;
    }

    @Override
    public int hashCode() {
        return nodeType != null ? nodeType.hashCode() : 0;
    }
}
