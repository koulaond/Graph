package api;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public abstract class AbstractGraphElement {

    private UUID uuid;
    private String label;
    private Map<String, Object> properties;

    protected AbstractGraphElement(){
        this.uuid = UUID.randomUUID();
    }

    public AbstractGraphElement(String label, Map<String, Object> properties) {
        this.label = label;
        this.properties = properties;
    }
}
