package api;

import lombok.NonNull;

public class GraphHandler {

    @NonNull
    private DefaultGraph graph;

    public Graph getGraph(){
        return graph;
    }


}
