package examples.testmodel;

import core.schema.assembly.ModelSchemaAssembler;
import repository.api.definitions.SchemaDefinition;

public class ModelSchemaAssembleExample {
  public static void main(String[] args) {
    ModelSchemaAssembler assembler = new ModelSchemaAssembler();
    SchemaDefinition testModelSchema = assembler.name("testModelSchema").basePackage("examples.testmodel").assemble();
    System.out.println(testModelSchema);
  }
}
