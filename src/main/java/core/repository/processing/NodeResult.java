package core.repository.processing;

import core.repository.data.DataActionResult;
import core.schema.definitions.NodeDefinition;

public class NodeResult {
    private DataActionResult dataActionResult;
    private NodeDefinition nodeDefinition;

    public NodeResult(DataActionResult dataActionResult, NodeDefinition nodeDefinition) {
        this.dataActionResult = dataActionResult;
        this.nodeDefinition = nodeDefinition;
    }

    public DataActionResult getDataActionResult() {
        return dataActionResult;
    }

    public NodeDefinition getNodeDefinition() {
        return nodeDefinition;
    }
}
