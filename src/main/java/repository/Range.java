package repository;

public class Range {

    public static Range INFINITE = new Range(0L, Long.MAX_VALUE);

    public Range(Long offset, Long itemsCount) {
        this.offset = offset;
        this.itemsCount = itemsCount;
    }

    private Long offset;

    private Long itemsCount;

    public Long getOffset() {
        return offset;
    }

    public Long getItemsCount() {
        return itemsCount;
    }
}
