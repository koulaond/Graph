package core.repository;

import core.repository.data.*;

public interface Storage {

    /************************************
     * CRUD operations
     ************************************/

    DataActionResult<ResultData> create(DataBucket newData);

    DataActionResult<UpdateResultData> update(DataBucket newData);

    DataActionResult<ResultData> read(Long nodeId);

    ActionResult delete(Long nodeId);

    /************************************
     * Composite operations
     ************************************/

    // TODO
}
