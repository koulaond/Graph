package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Getter
public abstract class AbstractGraphElement {

    private UUID uuid;
    private String label;
    private Map<String, Object> properties;

    protected AbstractGraphElement(){
        this.uuid = UUID.randomUUID();
    }

    public AbstractGraphElement(@NonNull String label, @NonNull Map<String, Object> properties) {
        this.label = label;
        this.properties = properties;
    }

    public Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(properties);
    }

    public void setLabel(@NonNull String label) {
        this.label = label;
    }

    public void addProperty(String key, Object value){
        this.properties.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractGraphElement that = (AbstractGraphElement) o;

        if (!uuid.equals(that.uuid)) return false;
        if (!label.equals(that.label)) return false;
        return getProperties().equals(that.getProperties());
    }
}
