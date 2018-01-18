package api;

import java.util.Map;
import java.util.UUID;

public interface GraphElement {

    UUID getUuid();

    String getLabel();

    Map<String, Object> getProperties();
}
