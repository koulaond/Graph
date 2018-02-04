package model;

import lombok.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toSet;

public class GraphContainer {
    private Map<UUID, Graph> graphs;

    public Graph getByUUID(UUID by){
        return graphs.get(by);
    }

    public Collection<Graph> getByLabel(@NonNull String label){
        return graphs.values()
                .stream()
                .filter(graph -> label.equals(graph.getLabel()))
                .collect(toSet());
    }

    public Collection<Graph> getAll(){
        return graphs.values();
    }


}
