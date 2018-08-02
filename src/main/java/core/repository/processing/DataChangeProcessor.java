package core.repository.processing;
import java.util.HashMap;
import java.util.Map;

import core.repository.context.id.IdProvider;
import core.repository.data.DataChangeCollection;
import core.repository.validation.exception.NodeDefinitionNotFoundException;
import core.schema.Schema;
import core.schema.definitions.NodeDefinition;

import static java.lang.String.format;

public class DataChangeProcessor {

    private Schema schema;
    private Map<Object, DataChangeCollection> collectionPool;
    IdProvider idProvider;

    public DataChangeProcessor(Schema schema, IdProvider idProvider) {
        this.schema = schema;
        this.idProvider = idProvider;
        this.collectionPool = new HashMap<>();
    }

    public void processData(Object data) {
        NodeDefinition nodeDefinitionForClass = getNodeDefinitionForClass(data.getClass());
        if(nodeDefinitionForClass == null) {
            throw new NodeDefinitionNotFoundException(format("Node definition not found for class %s", data.getClass().getName()));
        }
        // If object is already processed then exit process.
        if(collectionPool.containsKey(data)){
            return;
        }
        DataChangeCollection changeCollection = new DataChangeCollection(idProvider.getNextId());
        collectionPool.put(data, changeCollection);
    }

    private NodeDefinition getNodeDefinitionForClass(Class clazz) {
        return schema.getNodeDefinitions().get(clazz);
    }

}
