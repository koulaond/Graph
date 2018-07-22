package core.repository.validation;

import core.schema.Schema;

public class SchemaValidator {
    private Schema schema;

    public SchemaValidator(Schema schema) {
        this.schema = schema;
    }

    public boolean validate(Object toValidate) {
        return true;
    }

    public boolean validateObjectProperties(Object toValidate) {
        return true;
    }
}
