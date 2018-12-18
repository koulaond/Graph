package app.testmodel;

import core.schema.assembly.ModelSchemaAssembler;
import core.schema.assembly.definitions.SchemaDefinition;

public class ModelSchemaAssembleExample {
  public static void main(String[] args) {
    ModelSchemaAssembler assembler = new ModelSchemaAssembler();
    SchemaDefinition testModelSchema = assembler.name("testModelSchema").basePackage("app.testmodel").assemble();
    System.out.println(testModelSchema);
  }
}
