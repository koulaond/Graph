package model;

import java.util.HashMap;
import java.util.Map;

public class PropertyBuilder {
    private Map<String, Object> properties = new HashMap<>();

    public PropertyBuilder property(String key, Object value){
        this.properties.put(key, value);
        return this;
    }

    public Map<String, Object> build(){
        return new HashMap<>(properties);
    }
}
