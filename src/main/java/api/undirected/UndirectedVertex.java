package api.undirected;

import api.Vertex;

/**
 * Created by Koula on 11.11.2017.
 */
public interface UndirectedVertex<E extends UndirectedEdge>  extends Vertex<E>{
    boolean addEdge(E edge);

    default boolean containsEdge(E edge){
        return this.getAllEdges().contains(edge);
    }
}
