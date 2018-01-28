package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

public abstract class AbstractItem implements GraphElement {

    @NonNull
    @Getter
    protected UUID uuid = UUID.randomUUID();

    @NonNull
    @Setter
    @Getter
    protected String label;

    @NonNull
    @Setter
    protected Map<String, Object> properties;

    protected AbstractItem(@NonNull String label) {
        this.label = label;
        this.properties = new HashMap<>();
    }

    public Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    @Override
    public Object getPropertyValue(String key) {
        return this.properties.get(key);
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

    public boolean hasProperty(String key) {
        return this.properties.containsKey(key);
    }

    public boolean hasProperty(String key, Object value) {
        Object valueIn = this.properties.get(key);
        return Objects.equals(value, valueIn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractItem that = (AbstractItem) o;

        if (!uuid.equals(that.uuid)) return false;
        if (!label.equals(that.label)) return false;
        return getProperties().equals(that.getProperties());
    }
}
