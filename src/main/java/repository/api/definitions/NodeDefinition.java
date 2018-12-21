package repository.api.definitions;

import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

/**
 * Special Node class that describes @{@link model.Node}s of the given type.
 */
public class NodeDefinition extends AbstractDefinition {

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
    private Set<repository.api.definitions.property.PropertyDefinition> propertyDefinitions;

    public NodeDefinition(String name, boolean immutable, Long maxCount, Set<repository.api.definitions.property.PropertyDefinition> propertyDefinitions) {
        super(name);
        this.immutable = immutable;
        this.maxCount = requireNonNull(maxCount);
        this.propertyDefinitions = unmodifiableSet(requireNonNull(propertyDefinitions));
    }

    public NodeDefinition(String name) {
        this(name, false, Long.MAX_VALUE, emptySet());
    }

    public boolean isImmutable() {
        return immutable;
    }

    public Long getMaxCount() {
        return maxCount;
    }

    public Set<repository.api.definitions.property.PropertyDefinition> getPropertyDefinitions() {
        return propertyDefinitions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeDefinition nodeDefinition = (NodeDefinition) o;

        return name != null ? name.equals(nodeDefinition.getName()) : nodeDefinition.getName() == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
