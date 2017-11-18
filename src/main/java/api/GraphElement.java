package api;

import java.util.Map;
import java.util.UUID;


public interface GraphElement {

    UUID getId();

    String getLabel();

    void setLabel(String label);

    Map<String, Object> getProperties();

    boolean addProperty(String propName, Object propValue);

    void setProperty(String propName, Object propValue);

    Object deleteProperty(String propName);
}
