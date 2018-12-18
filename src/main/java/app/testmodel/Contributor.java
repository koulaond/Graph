package app.testmodel;

import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;

public abstract class Contributor {

  @StringProperty(name = "firstName", maxLength = 255, immutable = true)
  protected String firstName;

  @StringProperty(name = "lastName", nonNull = true, immutable = true, maxLength = 255)
  protected String lastName;

  @NumericProperty(name = "age", minValue = 0)
  protected int age;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }
}
