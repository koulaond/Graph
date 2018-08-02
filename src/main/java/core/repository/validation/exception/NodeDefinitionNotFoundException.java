package core.repository.validation.exception;

public class NodeDefinitionNotFoundException extends RuntimeException {

  public NodeDefinitionNotFoundException(String message) {
    super(message);
  }

  public NodeDefinitionNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
