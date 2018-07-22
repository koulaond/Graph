package core.repository.data;

import core.repository.ResultStatus;

public class ActionResult {
    protected ResultStatus status;
    protected Long nodeId;
    protected ActionType actionType;

    public ActionResult(ResultStatus status, Long nodeId, ActionType actionType) {
        this.status = status;
        this.nodeId = nodeId;
        this.actionType = actionType;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
