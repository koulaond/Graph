package api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public abstract class AbstractGraphElement implements GraphElement {

    protected final UUID id;
    protected String label;
    protected final Map<String, Object> properties;

    protected AbstractGraphElement(){
        this.id = UUID.randomUUID();
        this.properties = new HashMap<>();
    }

    @Override
    public UUID getId(){
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label){
        this.label = label;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }
}
