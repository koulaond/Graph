package api.undirected;

import api.AbstractEdge;

import java.util.Map;

public class SimpleUndirectedEdge<V extends UndirectedVertex>
        extends AbstractEdge<V>
        implements UndirectedEdge<V> {

    protected SimpleUndirectedEdge(String label,
                                   Map<String, Object> properties,
                                   int degree,
                                   V sourceVertex,
                                   V targetVertex) {
        super(label, properties, degree, sourceVertex, targetVertex);
    }

    @Override
    public boolean equals(Object obj) {
        return !(!super.equals(obj) || !(obj instanceof SimpleUndirectedEdge));
    }

    public static SimpleUndirectedEdgeBuilder<? extends UndirectedVertex> builder(){
        return new SimpleUndirectedEdgeBuilder<>();
    }

    private static class SimpleUndirectedEdgeBuilder<V extends UndirectedVertex> extends AbstractEdgeBuilder<V> {

        public SimpleUndirectedEdge<V> build() {
            return new SimpleUndirectedEdge<V>(
                    this.label,
                    this.properties,
                    this.degree,
                    this.sourceVertex,
                    this.targetVertex);
        }
    }

}
