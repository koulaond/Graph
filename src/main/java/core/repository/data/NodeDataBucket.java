package core.repository.data;

import java.util.HashMap;
import java.util.UUID;

/**
 * {@link HashMap} extension for carry data changes. Key of map entry is changed property name,
 * value is data unit object containing new value.
 */
public class NodeDataBucket extends HashMap<String, DataUnit> {

    //ID for the node to be created from this collection
    private UUID nodeId;
    
    public NodeDataBucket(UUID nodeId) {
        this.nodeId = nodeId;
    }

    public NodeDataBucket() {
        this(null);
    }

    public UUID getNodeId() {
        return nodeId;
    }
}
