package repository.schema.graphbuild;

import repository.schema.descriptions.NodeDescription;
import repository.schema.metamodel.MetaNode;

import java.util.Collection;
import java.util.Set;

public interface MetaNodeBuilder {

    public Collection<MetaNode> buildGraph(Set<NodeDescription> nodeDescriptions);
}
