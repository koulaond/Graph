package repository.schema.metamodel;

import java.util.Date;

public interface GraphDefinition {
    Long getId();

    String getSchema();

    Date getCreated();

    Date getLastModified();
}
