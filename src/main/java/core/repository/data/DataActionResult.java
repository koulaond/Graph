package core.repository.data;

import core.repository.ResultStatus;

import java.util.Set;

public class DataActionResult<D extends ResultData> extends ActionResult {
    protected Set<D> data;

    public DataActionResult(ResultStatus status, Long nodeId, ActionType actionType, Set<D> data) {
        super(status, nodeId, actionType);
        this.data = data;
    }

    public Set<D> getData() {
        return data;
    }
}
