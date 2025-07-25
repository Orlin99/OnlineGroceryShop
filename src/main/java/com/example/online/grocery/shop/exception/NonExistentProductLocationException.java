package com.example.online.grocery.shop.exception;

public class NonExistentProductLocationException extends RuntimeException {

  public NonExistentProductLocationException(String message) {
    super(message);
  }

  public NonExistentProductLocationException(String message, Throwable cause) {
    super(message, cause);
  }
}
