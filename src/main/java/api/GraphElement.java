package api;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public interface GraphElement {

    UUID getUuid();

    String getLabel();

    Map<String, Object> getProperties();

    Object getPropertyValue(String key);

    boolean hasProperty(String key);

    boolean hasProperty(String key, Object value);

    boolean hasProperties(String key1, String key2);

    boolean hasProperties(String key1, String key2, String key3);

    boolean hasProperties(Set<String> properties);

    boolean hasProperties(String key1, Object value1, String key2, Object value2);

    boolean hasProperties(String key1, Object value1, String key2, Object value2, String key3, Object value3);

    boolean hasProperties(Map<String, Object> properties);
}
