package core.schema.validation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import core.schema.descriptions.DatePropertyDescription;
import core.schema.descriptions.NumericPropertyDescription;
import core.schema.descriptions.PropertyDescription;

public class PropertyValidatorProvider {

  private Map<Class<? extends Serializable>, Map<Class<? extends PropertyDescription>, PropertyValidator>> cache;

  public PropertyValidatorProvider() {
    this.cache = new HashMap<>();
    initCache();
  }


  public <P extends Serializable, PD extends PropertyDescription<P>> PropertyValidator forProperty(Class<P> propertyClass, Class<PD> propertyDescriptionClass) {
    Map<Class<? extends PropertyDescription>, PropertyValidator> innerMap = cache.get(propertyClass);
    if (innerMap != null) {
      return innerMap.get(propertyDescriptionClass);
    }
    return null;
  }

  // TODO another, more composite validators will be implemented
  private void initCache() {
    initString();
    initNumeric();
    initDate();
  }

  private void initDate() {
    Map<Class<? extends PropertyDescription>, PropertyValidator> dateMap = new HashMap<>();
    dateMap.put(DatePropertyDescription.class, new DatePropertyValidator());
    cache.put(Date.class, dateMap);
  }

  private void initNumeric() {
    Map<Class<? extends PropertyDescription>, PropertyValidator> numericMap = new HashMap<>();
    numericMap.put(NumericPropertyDescription.class, new NumericPropertyValidator());
    cache.put(BigDecimal.class, numericMap);
  }

  private void initString() {
    Map<Class<? extends PropertyDescription>, PropertyValidator> stringMap = new HashMap<>();
    stringMap.put(DatePropertyDescription.class, new DatePropertyValidator());
    cache.put(String.class, stringMap);
  }
}
