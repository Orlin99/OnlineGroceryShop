package com.example.online.grocery.shop.exception;

public class ShortageOfGoodsException extends RuntimeException {

  public ShortageOfGoodsException(String message) {
    super(message);
  }

  public ShortageOfGoodsException(String message, Throwable cause) {
    super(message, cause);
  }
}
