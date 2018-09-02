package core.repository.data;

import java.util.Collection;

public class NodeChange {
  private Long nodeId;
  private Collection<DataUnitChange> dataUnitChanges;

  public NodeChange(Long nodeId, Collection<DataUnitChange> dataUnitChanges) {
    this.nodeId = nodeId;
    this.dataUnitChanges = dataUnitChanges;
  }

  public Long getNodeId() {
    return nodeId;
  }

  public Collection<DataUnitChange> getDataUnitChanges() {
    return dataUnitChanges;
  }
}
