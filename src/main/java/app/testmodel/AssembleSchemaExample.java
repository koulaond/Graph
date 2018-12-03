package app.testmodel;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import core.schema.assembly.NodeBuilder;
import core.schema.assembly.RelationBuilder;
import core.schema.assembly.SchemaAssembler;
import core.schema.assembly.definitions.SchemaDefinition;
import core.schema.assembly.definitions.property.DatePropertyDefinition;
import core.schema.assembly.definitions.property.EnumPropertyDefinition;
import core.schema.assembly.definitions.property.NumericPropertyDefinition;
import core.schema.assembly.definitions.property.StringPropertyDefinition;
import model.Direction;

public class AssembleSchemaExample {

  public static void main(String[] args) {
    SchemaDefinition schemaDefinition = new SchemaAssembler()
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
