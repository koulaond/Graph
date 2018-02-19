package repository.schema;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import repository.schema.properties.PropertyDescription;

import java.util.Set;

@Getter
@RequiredArgsConstructor
@Builder
public class TypeDescription<T> {

    Class<T> describedClass;

    private Set<PropertyDescription> propertyDescriptions;
}
