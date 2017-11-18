package api;

import lombok.NonNull;

import java.util.Map;
import java.util.UUID;


public interface GraphElement {

    UUID getId();

    String getLabel();

    void setLabel(@NonNull String label);

    Map<String, Object> getProperties();

    boolean addProperty(@NonNull String propName, @NonNull Object propValue);

    void setProperty(@NonNull String propName, @NonNull Object propValue);

    Object deleteProperty(@NonNull String propName);
}
