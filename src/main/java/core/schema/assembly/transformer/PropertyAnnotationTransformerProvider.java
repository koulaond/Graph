package core.schema.assembly.transformer;

import java.lang.annotation.Annotation;

import core.schema.annotations.properties.DateProperty;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;

public class PropertyAnnotationTransformerProvider {

  public PropertyAnnotationTransformer forPropertyAnnotation(Annotation annotation) {
    if(DateProperty.class.equals(annotation.annotationType())){
      return new DatePropertyAnnotationTransformer();
    }

    if(EnumProperty.class.equals(annotation.annotationType())){
      return new EnumPropertyAnnotationTransformer();
    }

    if(NumericProperty.class.equals(annotation.annotationType())){
      return new NumericPropertyAnnotationTransformer();
    }

    if(StringProperty.class.equals(annotation.annotationType())){
      return new StringPropertyAnnotationTransformer();
    }

    return null;
  }
}
