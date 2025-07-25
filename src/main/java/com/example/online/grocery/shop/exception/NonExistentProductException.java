package com.example.online.grocery.shop.exception;

public class NonExistentProductException extends RuntimeException {

  public NonExistentProductException(String message) {
    super(message);
  }

  public NonExistentProductException(String message, Throwable cause) {
    super(message, cause);
  }
}
