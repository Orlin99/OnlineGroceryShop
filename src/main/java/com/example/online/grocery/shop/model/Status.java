package com.example.online.grocery.shop.model;

import com.example.online.grocery.shop.utility.StatusAttributeConverter;

public enum Status {
  SUCCESS("success"), FAIL("fail");

  private static final String MESSAGE_FOR_UNKNOWN_STATUS_VALUE = "Unknown Status Value: ";

  private String value;

  Status(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Status fromValue(String value) {
    for (Status status : Status.values()) {
      if (status.value.equals(value)) {
        return status;
      }
    }
    throw new IllegalArgumentException(MESSAGE_FOR_UNKNOWN_STATUS_VALUE + value);
  }

  public static Status fromString(String value) {
    String statusToUpperCase = value.toLowerCase();
    return new StatusAttributeConverter().convertToEntityAttribute(statusToUpperCase);
  }

  @Override
  public String toString() {
    return "Status [value=" + value + "]";
  }
}
