package com.example.online.grocery.shop.exception;

public class InvalidValueForQuantityException extends RuntimeException {

  public InvalidValueForQuantityException(String message) {
    super(message);
  }

  public InvalidValueForQuantityException(String message, Throwable cause) {
    super(message, cause);
  }
}
