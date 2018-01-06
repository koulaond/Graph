package api;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractGraphElement implements GraphElement {

    protected final UUID id;
    protected String label;
    protected Map<String, Object> properties;

    protected AbstractGraphElement(@NonNull String label,
                                   @NonNull Map<String, Object> properties) {
        this.id = UUID.randomUUID();
        this.label = label;
        this.properties = properties;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(@NonNull String label) {
        this.label = label;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public boolean addProperty(@NonNull String propName, @NonNull Object propValue) {
        if (properties.containsKey(propName)) {
            return false;
        } else {
            setProperty(propName, propValue);
            return true;
        }
    }

    @Override
    public void setProperty(@NonNull String propName, @NonNull Object propValue) {
        properties.put(propName, propValue);
    }

    @Override
    public Object deleteProperty(@NonNull String propName) {
        return properties.remove(propName);
    }

    @Override
    public boolean equals(Object obj) {
        if(!super.equals(obj)){
            return false;
        }
        if(!(obj instanceof AbstractGraphElement)){
            return false;
        }
        AbstractGraphElement that = (AbstractGraphElement) obj;
        if(!getId().equals(that.getId())){
            return false;
        }
        if(!getProperties().equals(that.properties)){
            return false;
        }
        return true;
    }

    protected static abstract class AbstractGraphElementBuilder {

        protected String label;
        protected Map<String, Object> properties;

        public AbstractGraphElementBuilder() {
            this.properties = new HashMap<>();
        }

        public AbstractGraphElementBuilder label(String label) {
            this.label = label;
            return this;
        }

        public AbstractGraphElementBuilder properties(Map<String, Object> properties) {
            this.properties.putAll(properties);
            return this;
        }

        public AbstractGraphElementBuilder property(String propName, Object propValue) {
            this.properties.put(propName, propValue);
            return this;
        }
    }
}
