package core.schema.assembly.transformer;

import core.schema.annotations.properties.DateProperty;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;

public class PropertyAnnotationTransformerFactory {

  public PropertyAnnotationTransformer forPropertyAnnotation(Class annotationType) {
    if(DateProperty.class.equals(annotationType)){
      return new DatePropertyAnnotationTransformer();
    }

    if(EnumProperty.class.equals(annotationType)){
      return new EnumPropertyAnnotationTransformer();
    }

    if(NumericProperty.class.equals(annotationType)){
      return new NumericPropertyAnnotationTransformer();
    }

    if(StringProperty.class.equals(annotationType)){
      return new StringPropertyAnnotationTransformer();
    }

    return null;
  }
}
