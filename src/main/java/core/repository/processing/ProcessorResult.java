package core.repository.processing;

import core.repository.ResultStatus;

import java.util.HashMap;

public class ProcessorResult extends HashMap<Long, NodeResult> {
    private ResultStatus status;

    public ProcessorResult(ResultStatus status) {
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }
}
