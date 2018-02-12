package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractEntity extends AbstractItem implements Entity {

    @NonNull
    @Setter
    protected Map<String, Object> properties;

    @Getter
    protected Graph parentGraph;

    protected AbstractEntity(String label) {
        super(label);
        this.properties = new HashMap<>();
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

    public void addProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    public void removeProperty(String key) {
        this.properties.remove(key);
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
}
