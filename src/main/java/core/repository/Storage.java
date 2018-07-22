package core.repository;

import core.repository.data.*;

public interface Storage {

    /************************************
     * CRUD operations
     ************************************/

    DataActionResult<DataUnit> create(DataChangeCollection newData);

    DataActionResult<UpdateDataUnit> update(DataChangeCollection newData);

    DataActionResult<DataUnit> read(Long nodeId);

    ActionResult delete(Long nodeId);

    /************************************
     * Composite operations
     ************************************/

    // TODO
}
