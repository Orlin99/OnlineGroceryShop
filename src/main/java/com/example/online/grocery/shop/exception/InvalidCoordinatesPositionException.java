package com.example.online.grocery.shop.exception;

public class InvalidCoordinatesPositionException extends RuntimeException {

  public InvalidCoordinatesPositionException(String message) {
    super(message);
  }

  public InvalidCoordinatesPositionException(String message, Throwable cause) {
    super(message, cause);
  }
}
