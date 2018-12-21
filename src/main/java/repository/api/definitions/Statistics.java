package repository.api.definitions;

public class Statistics {
  private Long nodesCount;
  private Long relationsCount;
  private Long graphSize;

  public Statistics(Long nodesCount, Long relationsCount, Long graphSize) {
    this.nodesCount = nodesCount;
    this.relationsCount = relationsCount;
    this.graphSize = graphSize;
  }

  public Long getNodesCount() {
    return nodesCount;
  }

  public Long getRelationsCount() {
    return relationsCount;
  }

  public Long getGraphSize() {
    return graphSize;
  }
}
