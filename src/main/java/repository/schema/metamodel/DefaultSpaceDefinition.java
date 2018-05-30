package repository.schema.metamodel;

import java.util.UUID;

public class DefaultSpaceDefinition implements SpaceDefinition {

    protected UUID id;
    protected String name;

    public DefaultSpaceDefinition(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
