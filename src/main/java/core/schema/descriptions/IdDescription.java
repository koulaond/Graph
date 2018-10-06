package core.schema.descriptions;

import java.util.UUID;

public class IdDescription extends Description<UUID> {

    public IdDescription(String idPropertyName) {
        super(idPropertyName, UUID.class);
    }
}
