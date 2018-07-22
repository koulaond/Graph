package core.repository.validation;

import java.util.Map;

import core.schema.Schema;
import core.schema.definitions.NodeDefinition;
import core.schema.descriptions.PropertyDescription;

public class SchemaValidator {
    private Schema schema;

    public SchemaValidator(Schema schema) {
        this.schema = schema;
    }

    public boolean validate(Object toValidate) {
        return true;
    }

    public boolean validateObjectProperties(Object toValidate) {
        NodeDefinition definitionForObject = schema.getNodeDefinitions().get(toValidate.getClass());
        Map<String, PropertyDescription> propertyDescriptionsByFields = definitionForObject.getCache().getPropertyDescriptionsByFieldName();

        return true;
    }
}
