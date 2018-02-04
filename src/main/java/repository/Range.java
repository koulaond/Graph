package repository;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Range {

    public static Range INFINITE = Range.builder()
            .offset(0L)
            .itemsCount(Long.MAX_VALUE)
            .build();

    private Long offset;

    private Long itemsCount;
}
