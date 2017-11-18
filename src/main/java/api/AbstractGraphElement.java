package api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public abstract class AbstractGraphElement implements GraphElement {

    protected final UUID id;
    protected String label;
    protected Map<String, Object> properties;

    protected AbstractGraphElement(String label, Map<String, Object> properties) {
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
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public boolean addProperty(String propName, Object propValue) {
        if (properties.containsKey(propName)) {
            return false;
        } else {
            setProperty(propName, propValue);
            return true;
        }
    }

    @Override
    public void setProperty(String propName, Object propValue) {
        properties.put(propName, propValue);
    }

    @Override
    public Object deleteProperty(String propName) {
        return properties.remove(propName);
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
