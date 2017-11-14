package api.undirected;

import api.AbstractEdge;

public class SimpleUndirectedEdge<V extends UndirectedVertex>
        extends AbstractEdge<V>
        implements UndirectedEdge<V> {

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SimpleUndirectedEdge)) return false;
        SimpleUndirectedEdge that = (SimpleUndirectedEdge) obj;
        return (that.getSourceVertex().equals(sourceVertex) && that.getTargetVertex().equals(targetVertex))
                || (that.getSourceVertex().equals(targetVertex) && that.getTargetVertex().equals(sourceVertex));
    }

}
