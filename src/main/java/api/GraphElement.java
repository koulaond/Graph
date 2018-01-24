package api;

import java.util.Map;
import java.util.UUID;

public interface GraphElement {

    UUID getUuid();

    String getLabel();

    Map<String, Object> getProperties();

    boolean hasProperty(String key);

    boolean hasProperty(String key, Object value);
}
