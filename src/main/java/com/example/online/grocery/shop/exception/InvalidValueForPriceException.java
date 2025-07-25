package com.example.online.grocery.shop.exception;

public class InvalidValueForPriceException extends RuntimeException {

  public InvalidValueForPriceException(String message) {
    super(message);
  }

  public InvalidValueForPriceException(String message, Throwable cause) {
    super(message, cause);
  }
}
