package core.schema.definitions;

import java.util.Date;

public interface GraphDefinition{
    Long getId();

    Date getCreated();

    Date getLastModified();
}
