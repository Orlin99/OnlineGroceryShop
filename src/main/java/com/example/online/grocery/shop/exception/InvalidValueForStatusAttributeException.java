package com.example.online.grocery.shop.exception;

public class InvalidValueForStatusAttributeException extends RuntimeException {

  public InvalidValueForStatusAttributeException(String message) {
    super(message);
  }

  public InvalidValueForStatusAttributeException(String message, Throwable cause) {
    super(message, cause);
  }
}
