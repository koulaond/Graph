package core.repository.data;

public class Error {

  private ErrorCode errorCode;
  private String message;

  public Error(ErrorCode errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public String getMessage() {
    return message;
  }
}
