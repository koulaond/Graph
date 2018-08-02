package core.repository.data;

import java.util.HashMap;

/**
 * {@link HashMap} extension for carry data changes.
 */
public class DataChangeCollection<D extends DataChange> extends HashMap<String, D> {
    private Long nodeId;


    public DataChangeCollection(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getNodeId() {
        return nodeId;
    }
}
