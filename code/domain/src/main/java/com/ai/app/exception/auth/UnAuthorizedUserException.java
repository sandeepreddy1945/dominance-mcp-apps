package com.ai.app.exception.auth;

public class UnAuthorizedUserException extends RuntimeException {

  public UnAuthorizedUserException(String message) {
    super(message);
  }
}
