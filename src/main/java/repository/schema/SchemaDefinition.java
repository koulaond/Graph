package repository.schema;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import repository.schema.descriptions.TypeDescription;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class SchemaDefinition {

    private String name;
    private Map<Class, TypeDescription> typeDescriptions;
}
