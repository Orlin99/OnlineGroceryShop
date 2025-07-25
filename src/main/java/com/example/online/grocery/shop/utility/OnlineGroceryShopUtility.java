package com.example.online.grocery.shop.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.online.grocery.shop.dto.OrderDTO;
import com.example.online.grocery.shop.dto.OrderItemDTO;
import com.example.online.grocery.shop.dto.ProductDTO;
import com.example.online.grocery.shop.exception.InvalidCoordinatesPositionException;
import com.example.online.grocery.shop.exception.InvalidValueForPriceException;
import com.example.online.grocery.shop.exception.InvalidValueForProductNameException;
import com.example.online.grocery.shop.exception.InvalidValueForQuantityException;
import com.example.online.grocery.shop.exception.NonExistentProductException;
import com.example.online.grocery.shop.exception.ShortageOfGoodsException;
import com.example.online.grocery.shop.model.Location;
import com.example.online.grocery.shop.model.OrderItem;
import com.example.online.grocery.shop.model.Orders;
import com.example.online.grocery.shop.model.Products;
import com.example.online.grocery.shop.repository.ProductsRepository;

public final class OnlineGroceryShopUtility {

  private OnlineGroceryShopUtility() {
    // Default Empty Constructor
  }

  public static final String MESSAGE_FOR_INVALID_VALUE_FOR_STATUS_ATTRIBUTE = "Status Attribute Cannot Be Null";
  public static final String SUCCESSFULLY_CREATED_PRODUCT_RESPONSE = "The Product Was Created Successfully";
  public static final String SUCCESSFULLY_UPDATED_PRODUCT_RESPONSE = "The Product Was Updated Successfully";
  public static final String SUCCESSFULLY_REMOVED_PRODUCT_RESPONSE = "The Product Was Deleted Successfully";
  public static final String SUCCESSFULLY_CREATED_ORDER_RESPONSE = "The Order Was Created Successfully";

  public static final String ERROR_FOR_EXISTING_PRODUCT_RESPONSE = "This Product Already Exists";
  public static final String ERROR_FOR_TAKEN_LOCATION_RESPONSE = "This Location Is Already Taken By Another Product";
  public static final String ERROR_FOR_NON_EXISTING_PRODUCT_RESPONSE = "This Product Does Not Exists";
  public static final String ERROR_FOR_NON_EXISTING_PRODUCT_LOCATION_RESPONSE = "This Product Location Does Not Exists";

  private static final Integer MINIMUM_QUANTITY_VALUE = 0;
  private static final String ERROR_FOR_NEGATIVE_QUANTITY_VALUE = "The Quantity Should Be Positive Number";
  private static final Integer MINIMUM_POSITION_VALUE = 0;
  private static final String ERROR_FOR_NEGATIVE_SINGLE_COORDINATE = "Both Coordinates Must Be Positive";
  private static final String ERROR_FOR_BOT_STARTING_POSITION = "You Cannot Put Grocery On The Bot's Position";
  private static final String ERROR_FOR_SHORTAGE_OF_GOODS = "Not Enough Quantity Of This Product In The Store. Please Order Less. Current Quantity Of This Product: ";
  private static final String ERROR_FOR_NULL_OR_EMPTY_PRODUCT_NAME = "The Product Must Have Valid Name";
  private static final int MIN_PRODUCT_LENGTH = 3;
  private static final int MAX_PRODUCT_LENGTH = 60;
  private static final String ERROR_FOR_PRODUCT_NAME_LENGTH = "The Product Name Should Be Between 3 And 60 Characters Long";
  private static final String PRODUCT_NAME_PATTERN = "^[a-zA-Z0-9\\-' ]+$";
  private static final String DEFAULT_ERROR_FOR_PRODUCT_NAME = "Invalid Product Name. Please Try Again";
  private static final String ERROR_FOR_NULL_VALUED_PRICE = "The Value Of The Price Cannot Be Null";
  private static final String ERROR_FOR_NAN_OR_INFINITE_VALUED_PRICE = "The Value Of The Price Cannot Be That Big And Must Be Real Number";
  private static final Double NEGATIVE_VALUED_PRICE = 0d;
  private static final String ERROR_FOR_NEGATIVE_VALUED_PRICE = "The Value Of The Price Must Be Positive Number";

  public static ProductDTO toDTO(Products product, Location location) {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName(product.getName());
    productDTO.setPrice(product.getPrice());
    productDTO.setQuantity(location.getQuantity());
    productDTO.setXPosition(location.getXPosition());
    productDTO.setYPosition(location.getYPosition());
    return productDTO;
  }

  public static Products productToEntity(ProductDTO productDTO) {
    Products product = new Products();
    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());
    return product;
  }

  public static Location locationToEntity(ProductDTO productDTO, Products product) {
    Location location = new Location();
    location.setQuantity(productDTO.getQuantity());
    location.setXPosition(productDTO.getXPosition());
    location.setYPosition(productDTO.getYPosition());
    location.setProduct(product);
    return location;
  }

  public static OrderDTO toDTO(Orders order) {
    OrderDTO orderDTO = new OrderDTO();
    List<OrderItem> items = order.getItems();
    List<OrderItemDTO> itemsToDTO = new ArrayList<>();
    for (OrderItem item : items) {
      String productName = item.getProduct().getName();
      Integer quantity = item.getQuantity();
      itemsToDTO.add(new OrderItemDTO(productName, quantity));
    }
    orderDTO.setItems(itemsToDTO);
    return orderDTO;
  }

  public static void validateProductData(String name, Double price) {
    validateProductName(name);
    validateProductPrice(price);
  }

  public static void validateQuantity(Integer quantity) {
    if ((quantity == null) || (quantity < MINIMUM_QUANTITY_VALUE)) {
      throw new InvalidValueForQuantityException(ERROR_FOR_NEGATIVE_QUANTITY_VALUE);
    }
  }

  public static void validateCoordinates(Integer xPosition, Integer yPosition) {
    if ((xPosition < MINIMUM_POSITION_VALUE) || (yPosition < MINIMUM_POSITION_VALUE)) {
      throw new InvalidCoordinatesPositionException(ERROR_FOR_NEGATIVE_SINGLE_COORDINATE);
    }
    if ((xPosition.equals(MINIMUM_POSITION_VALUE)) && (yPosition.equals(MINIMUM_POSITION_VALUE))) {
      throw new InvalidCoordinatesPositionException(ERROR_FOR_BOT_STARTING_POSITION);
    }
  }

  public static void validateItems(List<OrderItemDTO> items, ProductsRepository productsRepository) {
    for (OrderItemDTO item : items) {
      String productName = item.getProductName();
      validateProductName(productName);
      Optional<Products> existingProduct = productsRepository.findByName(productName);
      if (!existingProduct.isPresent()) {
        throw new NonExistentProductException(ERROR_FOR_NON_EXISTING_PRODUCT_RESPONSE);
      }
      Integer quantity = item.getQuantity();
      validateQuantity(quantity);
      Products product = existingProduct.get();
      Integer productQuantity = product.getLocation().getQuantity();
      if (quantity > productQuantity) {
        throw new ShortageOfGoodsException(ERROR_FOR_SHORTAGE_OF_GOODS + productQuantity);
      }
      Integer newQuantity = productQuantity - quantity;
      product.getLocation().setQuantity(newQuantity);
    }
  }

  private static void validateProductName(String name) {
    if ((name == null) || name.trim().isEmpty()) {
      throw new InvalidValueForProductNameException(ERROR_FOR_NULL_OR_EMPTY_PRODUCT_NAME);
    }
    String trimmedName = name.trim();
    if ((trimmedName.length() <= MIN_PRODUCT_LENGTH) || (trimmedName.length() >= MAX_PRODUCT_LENGTH)) {
      throw new InvalidValueForProductNameException(ERROR_FOR_PRODUCT_NAME_LENGTH);
    }
    if (!trimmedName.matches(PRODUCT_NAME_PATTERN)) {
      throw new InvalidValueForProductNameException(DEFAULT_ERROR_FOR_PRODUCT_NAME);
    }
  }

  private static void validateProductPrice(Double price) {
    if (price == null) {
      throw new InvalidValueForPriceException(ERROR_FOR_NULL_VALUED_PRICE);
    } else if (price.isNaN() || price.isInfinite()) {
      throw new InvalidValueForPriceException(ERROR_FOR_NAN_OR_INFINITE_VALUED_PRICE);
    } else if (price <= NEGATIVE_VALUED_PRICE) {
      throw new InvalidValueForPriceException(ERROR_FOR_NEGATIVE_VALUED_PRICE);
    }
  }
}
