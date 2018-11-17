package core.repository.data;

public enum ErrorCode {
  REPOSITORY_CHANGE("Error occurred during repository change.");

  private String cause;

  ErrorCode(String cause) {
    this.cause = cause;
  }

  public String getCause() {
    return cause;
  }
}
