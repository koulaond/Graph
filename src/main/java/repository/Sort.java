package repository;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Sort {

    private String fieldName;

    private SortDirection direction;
}
