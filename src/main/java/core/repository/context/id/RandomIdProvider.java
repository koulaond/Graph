package core.repository.context.id;

import java.util.Random;

public class RandomIdProvider implements IdProvider {

  @Override
  public Long getNextId() {
    return new Random(Long.MAX_VALUE).nextLong();
  }
}
