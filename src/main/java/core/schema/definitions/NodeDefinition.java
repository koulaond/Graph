package core.schema.definitions;

import java.util.Map;

import core.schema.descriptions.PropertyDescription;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Special Node class that describes @{@link model.Node}s of the given type.
 *
 * @param <T> described class
 */
public class NodeDefinition<T> {


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

    private NodeDefinitionCache cache;

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

    public NodeDefinitionCache getCache() {
        return cache;
    }

    public void cache(Map<String, PropertyDescription> propertyDescriptionsByPropertyName,
                      Map<String, RelationDefinition> relationshipDescriptionsByPropertyName) {
        this.cache =  new NodeDefinitionCache(propertyDescriptionsByPropertyName, relationshipDescriptionsByPropertyName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeDefinition<?> nodeDefinition = (NodeDefinition<?>) o;

        return nodeType != null ? nodeType.equals(nodeDefinition.nodeType) : nodeDefinition.nodeType == null;
    }

    @Override
    public int hashCode() {
        return nodeType != null ? nodeType.hashCode() : 0;
    }

    public static class NodeDefinitionCache {
        /**
         * Map of property and relation descriptions that describe properties, which can be stored in the
         * node of this type. The key to the map is property name.
         */
        private Map<String, PropertyDescription> propertyDescriptionsByPropertyName;
        private Map<String, RelationDefinition> relationshipDescriptionsByPropertyName;

        /**
         * Map of property and relation descriptions by their owner field names.
         */
        private Map<String, PropertyDescription> propertyDescriptionsByFieldName;
        private Map<String, RelationDefinition> relationDescriptionsByFieldName;

        private NodeDefinitionCache(Map<String, PropertyDescription> propertyDescriptionsByPropertyName,
                                   Map<String, RelationDefinition> relationshipDescriptionsByPropertyName) {

            this.propertyDescriptionsByPropertyName = propertyDescriptionsByPropertyName;
            this.relationshipDescriptionsByPropertyName = relationshipDescriptionsByPropertyName;
            this.propertyDescriptionsByFieldName = propertyDescriptionsByPropertyName.values()
                    .stream()
                    .collect(toMap(propertyDescription -> propertyDescription.getOwnerFieldName(), identity()));
            this.relationDescriptionsByFieldName = relationshipDescriptionsByPropertyName.values()
                    .stream()
                    .collect(toMap(propertyDescription -> propertyDescription.getOwnerFieldName(), identity()));
        }

        public Map<String, PropertyDescription> getPropertyDescriptionsByPropertyName() {
            return propertyDescriptionsByPropertyName;
        }

        public Map<String, RelationDefinition> getRelationshipDescriptionsByPropertyName() {
            return relationshipDescriptionsByPropertyName;
        }

        public Map<String, PropertyDescription> getPropertyDescriptionsByFieldName() {
            return propertyDescriptionsByFieldName;
        }

        public Map<String, RelationDefinition> getRelationDescriptionsByFieldName() {
            return relationDescriptionsByFieldName;
        }
    }
}
