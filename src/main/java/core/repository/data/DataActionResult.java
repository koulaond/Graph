package core.repository.data;

import core.repository.ResultStatus;

import java.util.Set;

public class DataActionResult<DU extends DataUnit> extends ActionResult {
    protected Set<DU> data;

    public DataActionResult(ResultStatus status, Long nodeId, ActionType actionType, Set<DU> data) {
        super(status, nodeId, actionType);
        this.data = data;
    }

    public Set<DU> getData() {
        return data;
    }
}
