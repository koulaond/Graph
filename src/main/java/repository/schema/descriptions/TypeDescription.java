package repository.schema.descriptions;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
@Builder
public class TypeDescription<T> {

    private Class<T> describedClass;

    private String typeName;

    private boolean immutable;

    private Set<PropertyDescription> propertyDescriptions;


}
