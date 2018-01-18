package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Getter
public abstract class AbstractItem {

    @NonNull
    private UUID uuid = UUID.randomUUID();

    @NonNull
    @Setter
    private String label;

    @NonNull
    private Map<String, Object> properties;

    protected AbstractItem(@NonNull String label, @NonNull Map<String, Object> properties) {
        this.label = label;
        this.properties = properties;
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
