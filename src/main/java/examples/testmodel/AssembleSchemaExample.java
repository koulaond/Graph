package examples.testmodel;

import core.schema.assembly.CustomSchemaAssembler;
import core.schema.assembly.NodeBuilder;
import core.schema.assembly.RelationBuilder;
import repository.api.definitions.SchemaDefinition;
import repository.api.definitions.property.DatePropertyDefinition;
import repository.api.definitions.property.EnumPropertyDefinition;
import repository.api.definitions.property.NumericPropertyDefinition;
import repository.api.definitions.property.StringPropertyDefinition;
import model.Direction;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AssembleSchemaExample {

  public static void main(String[] args) {
    SchemaDefinition schemaDefinition = new CustomSchemaAssembler()
        .name("testSchema")
        .additionalInfo("Description", "Schema assembly example.")
        .defineNodes()
        .node(new NodeBuilder()
            .nodeType("testImmutableNode")
            .immutable(true)
            .maxCount(6000L)
            .addProperty(new StringPropertyDefinition("testStringProp", true, false, false, 0, 130))
            .addProperty(new NumericPropertyDefinition("testNumericProp",true, false, true, new BigDecimal(20), new BigDecimal(100)))
            .build())
        .node(new NodeBuilder()
            .nodeType("testImmutableNode2")
            .immutable(true)
            .maxCount(6000L)
            .addProperty(new DatePropertyDefinition("testDateProp"))
            .build())
        .finish()
        .defineRelations()
        .relation(new RelationBuilder()
            .relationType("testRelation")
            .startNodeType("testImmutableNode")
            .endNodeType("testImmutableNode2")
            .direction(Direction.OUTGOING)
            .addProperty(new EnumPropertyDefinition("testEnumRelationProperty", Stream.of("Enum1", "Enum2", "Enum3").collect(Collectors.toSet())))
            .build())
        .finish()
        .assemble();
  }
}
