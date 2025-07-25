package com.example.online.grocery.shop.exception;

public class InvalidValueForProductNameException extends RuntimeException {

  public InvalidValueForProductNameException(String message) {
    super(message);
  }

  public InvalidValueForProductNameException(String message, Throwable cause) {
    super(message, cause);
  }
}
