package core.schema.definitions;

import java.util.Date;
import java.util.UUID;

public class GraphDefinition {

    /**
     * Graph identifier.
     */
    private UUID id;

    /**
     * Date where the schema was created.
     */
    private Date created;

    /**
     * Date where the schema was last time modified.
     */
    private Date lastModified;

    // TODO another fields, such as Users, roles, authorization, statistics...

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
