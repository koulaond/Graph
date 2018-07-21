package core.schema.descriptions;

public class IdDescription extends Description<Long> {

    public IdDescription(String idPropertyName) {
        super(idPropertyName, Long.class);
    }
}
