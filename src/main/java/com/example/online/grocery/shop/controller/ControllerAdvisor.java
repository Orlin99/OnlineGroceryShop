package com.example.online.grocery.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.online.grocery.shop.exception.InvalidCoordinatesPositionException;
import com.example.online.grocery.shop.exception.InvalidValueForPriceException;
import com.example.online.grocery.shop.exception.InvalidValueForProductNameException;
import com.example.online.grocery.shop.exception.InvalidValueForQuantityException;
import com.example.online.grocery.shop.exception.InvalidValueForStatusAttributeException;
import com.example.online.grocery.shop.exception.NonExistentProductException;
import com.example.online.grocery.shop.exception.NonExistentProductLocationException;
import com.example.online.grocery.shop.exception.ShortageOfGoodsException;

@ControllerAdvice(basePackages = "com.example.online.grocery.shop")
public class ControllerAdvisor {

  @ExceptionHandler(InvalidValueForPriceException.class)
  public ResponseEntity<String> handleInvalidValueForPriceException(InvalidValueForPriceException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @ExceptionHandler(InvalidValueForProductNameException.class)
  public ResponseEntity<String> handleInvalidValueForProductNameException(InvalidValueForProductNameException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @ExceptionHandler(InvalidValueForStatusAttributeException.class)
  public ResponseEntity<String> handleInvalidValueForStatusAttributeException(InvalidValueForStatusAttributeException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @ExceptionHandler(NonExistentProductException.class)
  public ResponseEntity<String> handleNonExistentProductException(NonExistentProductException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(InvalidValueForQuantityException.class)
  public ResponseEntity<String> handleInvalidValueForQuantityException(InvalidValueForQuantityException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @ExceptionHandler(InvalidCoordinatesPositionException.class)
  public ResponseEntity<String> handleInvalidCoordinatesPositionException(InvalidCoordinatesPositionException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }

  @ExceptionHandler(NonExistentProductLocationException.class)
  public ResponseEntity<String> handleNonExistentProductLocationException(NonExistentProductLocationException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(ShortageOfGoodsException.class)
  public ResponseEntity<String> handleShortageOfGoodsException(ShortageOfGoodsException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
}
