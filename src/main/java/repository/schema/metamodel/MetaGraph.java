package repository.schema.metamodel;

import java.util.Date;
import java.util.Set;

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

    /**
     * All meta-nodes in this schema describing meta-graph.
     */
    private Set<MetaNode> metaNodes;

    // TODO another fields, such as Users, roles, authorization...

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
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

    public Set<MetaNode> getMetaNodes() {
        return metaNodes;
    }

    public void setMetaNodes(Set<MetaNode> metaNodes) {
        this.metaNodes = metaNodes;
    }
}
