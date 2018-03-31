package repository.schema.descriptions;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
@Builder
public class TypeDescription<T> {

    Class<T> describedClass;

    private Set<PropertyDescription> propertyDescriptions;
}
