package core.repository.data;

import java.util.HashMap;

/**
 * {@link HashMap} extension for carry data changes.
 */
public class DataBucket<D extends DataUnit> extends HashMap<String, D> {

    //ID for the node to be created from this collection
    private Long nodeId;
    
    public DataBucket(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getNodeId() {
        return nodeId;
    }
}
