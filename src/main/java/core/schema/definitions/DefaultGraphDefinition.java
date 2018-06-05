package core.schema.definitions;

import java.util.Date;

public class DefaultGraphDefinition implements GraphDefinition {

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

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
