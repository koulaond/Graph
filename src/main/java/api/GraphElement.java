package api;

import java.util.Map;
import java.util.UUID;


public interface GraphElement {

    UUID getId();

    String getLabel();

    void setLabel(String label);

    Map<String, Object> getProperties();

}
