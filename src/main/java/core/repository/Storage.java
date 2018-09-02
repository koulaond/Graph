package core.repository;

import core.repository.data.*;

public interface Storage {

    /************************************
     * CRUD operations
     ************************************/

    DataActionResult<ResultData> create(NodeDataBucket newData);

    DataActionResult<UpdateResultData> update(NodeDataBucket newData);

    DataActionResult<ResultData> read(Long nodeId);

    ActionResult delete(Long nodeId);

    /************************************
     * Composite operations
     ************************************/

    // TODO
}
