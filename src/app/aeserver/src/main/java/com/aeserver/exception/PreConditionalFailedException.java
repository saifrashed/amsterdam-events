package com.aeserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class PreConditionalFailedException extends RuntimeException {
  public PreConditionalFailedException() {
    super();
  }
  public PreConditionalFailedException(String message, Throwable cause) {
    super(message, cause);
  }
  public PreConditionalFailedException(String message) {
    super(message);
  }
  public PreConditionalFailedException(Throwable cause) {
    super(cause);
  }
}
