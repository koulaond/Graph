package core.repository.context;

import core.schema.Schema;

public interface SchematicRepository extends Repository {
  Schema getSchema();
}
