package core.repository.processing;

import java.lang.annotation.Annotation;

public class Property<T> {
  private Class<T> propertyClass;
  private String propertyName;
  private T propertyValue;
  private Annotation propertyAnnotation;

  public Property(Class<T> propertyClass, String propertyName, T propertyValue, Annotation propertyAnnotation) {
    this.propertyClass = propertyClass;
    this.propertyName = propertyName;
    this.propertyValue = propertyValue;
    this.propertyAnnotation = propertyAnnotation;
  }

  public Class<T> getPropertyClass() {
    return propertyClass;
  }

  public String getPropertyName() {
    return propertyName;
  }

  public T getPropertyValue() {
    return propertyValue;
  }

  public Annotation getPropertyAnnotation() {
    return propertyAnnotation;
  }
}
