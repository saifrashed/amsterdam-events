package com.aeserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
  public UnAuthorizedException() {
    super();
  }

  public UnAuthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnAuthorizedException(String message) {
    super(message);
  }

  public UnAuthorizedException(Throwable cause) {
    super(cause);
  }
}
