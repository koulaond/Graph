package repository.schema.metamodel;


import java.util.Date;

public class MetaGraph {

    /**
     * Unique schema name that is defined by this meta-graph and its child meta-nodes and meta-relations.
     */
    private String schema;

    /**
     * Date where the schema was created.
     */
    private Date created;

    /**
     * Date where the schema was last time modified.
     */
    private Date lastModified;

    // TODO another fields, such as Users, roles, authorization...

    public MetaGraph(String schema, Date created, Date lastModified) {
        this.schema = schema;
        this.created = created;
        this.lastModified = lastModified;
    }

    public String getSchema() {
        return schema;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastModified() {
        return lastModified;
    }
}
