package repository.schema;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import repository.schema.descriptions.NodeDescription;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class SchemaDefinition {

    private String name;
    private Map<Class, NodeDescription> typeDescriptions;
}
