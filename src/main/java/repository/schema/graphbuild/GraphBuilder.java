package repository.schema.graphbuild;

import repository.schema.descriptions.NodeDescription;
import repository.schema.metamodel.MetaGraph;

import java.util.Set;

public interface GraphBuilder {

    public MetaGraph buildGraph(Set<NodeDescription> nodeDescriptions);
}
