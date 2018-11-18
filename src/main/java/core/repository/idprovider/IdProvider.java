package core.repository.idprovider;

/**
 * Unique identifier provider interface.
 */
public interface IdProvider {

  /**
   * Returns next available and unused ID.
   */
  Long getNextId();
}
