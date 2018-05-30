package model;

import java.util.*;

public abstract class GraphEntity implements HasId, HasProperties {

    protected UUID id;

    protected Map<String, Object> properties;

    protected Graph parentGraph;

    public GraphEntity(UUID id, Map<String, Object> properties, Graph parentGraph) {
        this.id = id;
        this.properties = properties;
        this.parentGraph = parentGraph;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public Graph getParentGraph() {
        return parentGraph;
    }

    @Override
    public Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    @Override
    public Object getPropertyValue(String key) {
        return this.properties.get(key);
    }

    @Override
    public Collection<String> getPropertyKeys(){
        return this.properties.keySet();
    }

    @Override
    public boolean hasProperties(String key1, Object value1, String key2, Object value2) {
        return hasProperty(key1, value1) && hasProperty(key2, value2);
    }

    @Override
    public boolean hasProperties(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        return hasProperties(key1, value1, key2, value2) && hasProperty(key3, value3);
    }

    @Override
    public boolean hasProperties(Map<String, Object> properties) {
        return properties.entrySet()
                .stream()
                .allMatch(entry -> this.properties.containsKey(entry.getKey())
                        && this.properties.containsValue(entry.getValue()));
    }

    @Override
    public boolean hasProperties(String key1, String key2) {
        return hasProperty(key1) && hasProperty(key2);
    }

    @Override
    public boolean hasProperties(String key1, String key2, String key3) {
        return hasProperty(key1) && hasProperty(key2) && hasProperty(key3);
    }

    @Override
    public boolean hasProperties(Set<String> properties) {
        return false;
    }

    @Override
    public boolean hasProperty(String key) {
        return this.properties.containsKey(key);
    }

    @Override
    public boolean hasProperty(String key, Object value) {
        Object valueIn = this.properties.get(key);
        return Objects.equals(value, valueIn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphEntity that = (GraphEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
