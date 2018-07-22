package core.context;

import core.schema.Schema;

import java.util.Collection;
import java.util.Map;

public class RepositoryContext {
    /**
     * Context singleton instance.
     */
    private static RepositoryContext context;

    private Map<String, Schema> schemas;

    private RepositoryContext() {
        // TODO create context by singleton builder
    }

    Collection<Schema> getAllSchemas(){
        return schemas.values();
    }

    Schema getSchema(String schemaName){
        return schemas.get(schemaName);
    }

    public static RepositoryContext instance() {
        return context;
    }

}
