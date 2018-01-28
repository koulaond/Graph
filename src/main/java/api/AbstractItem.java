package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

public abstract class AbstractItem {

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

    public void addProperty(String key, Object value){
        this.properties.put(key, value);
    }

    public void removeProperty(String key){
        this.properties.remove(key);
    }

    public boolean hasProperty(String key){
        return this.properties.containsKey(key);
    }

    public boolean hasProperty(String key, Object value){
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
