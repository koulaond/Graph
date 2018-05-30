package repository.schema.metamodel;

import java.util.Date;

public interface GraphDefinition{
    Long getId();

    Date getCreated();

    Date getLastModified();
}
