package core.repository;

import core.repository.data.*;

public interface Storage {

    /************************************
     * CRUD operations
     ************************************/

    DataActionResult<ResultData> create(DataChangeCollection newData);

    DataActionResult<UpdateResultData> update(DataChangeCollection newData);

    DataActionResult<ResultData> read(Long nodeId);

    ActionResult delete(Long nodeId);

    /************************************
     * Composite operations
     ************************************/

    // TODO
}
