package com.example.online.grocery.shop.utility;

import com.example.online.grocery.shop.exception.InvalidValueForStatusAttributeException;
import com.example.online.grocery.shop.model.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusAttributeConverter implements AttributeConverter<Status, String> {

  @Override
  public String convertToDatabaseColumn(Status attribute) {
    if (attribute == null) {
      throw new InvalidValueForStatusAttributeException(OnlineGroceryShopUtility.MESSAGE_FOR_INVALID_VALUE_FOR_STATUS_ATTRIBUTE);
    }
    return attribute.getValue();
  }

  @Override
  public Status convertToEntityAttribute(String databaseData) {
    if (databaseData == null) {
      throw new InvalidValueForStatusAttributeException(OnlineGroceryShopUtility.MESSAGE_FOR_INVALID_VALUE_FOR_STATUS_ATTRIBUTE);
    }
    return Status.fromValue(databaseData);
  }
}
