package core.schema.assembly.definitions;

import java.util.Set;

import core.schema.assembly.definitions.property.PropertyDefinition;

import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

/**
 * Special Node class that describes @{@link model.Node}s of the given type.
 */
public class NodeDefinition {

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
     * Set of property definitions (descriptions of node child properties)
     */
    private Set<PropertyDefinition> propertyDefinitions;

    public NodeDefinition(String nodeType, boolean immutable, Long maxCount, Set<PropertyDefinition> propertyDefinitions) {
        this.nodeType = requireNonNull(nodeType);
        this.immutable = immutable;
        this.maxCount = requireNonNull(maxCount);
        this.propertyDefinitions = unmodifiableSet(requireNonNull(propertyDefinitions));
    }

    public NodeDefinition(String nodeType) {
        this(nodeType, false, Long.MAX_VALUE, emptySet());
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

    public Set<PropertyDefinition> getPropertyDefinitions() {
        return propertyDefinitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeDefinition nodeDefinition = (NodeDefinition) o;

        return nodeType != null ? nodeType.equals(nodeDefinition.getNodeType()) : nodeDefinition.getNodeType() == null;
    }

    @Override
    public int hashCode() {
        return nodeType != null ? nodeType.hashCode() : 0;
    }

}