package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AbstractVertex extends AbstractNode implements Vertex {

    protected AbstractVertex(@NonNull String label,
                             @NonNull Map<String, Object> properties,
                             @NonNull Graph parentGraph) {
        super(label, properties, parentGraph);
    }

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (that instanceof AbstractVertex) {
            return true;
        }
        return false;
    }
}
