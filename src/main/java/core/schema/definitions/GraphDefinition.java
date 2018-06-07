package core.schema.definitions;

import java.util.Date;

public class GraphDefinition {

    /**
     * Graph identifier.
     */
    private Long id;

    /**
     * Date where the schema was created.
     */
    private Date created;

    /**
     * Date where the schema was last time modified.
     */
    private Date lastModified;

    // TODO another fields, such as Users, roles, authorization...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
