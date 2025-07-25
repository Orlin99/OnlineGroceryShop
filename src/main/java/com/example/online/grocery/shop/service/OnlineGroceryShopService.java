package com.example.online.grocery.shop.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.online.grocery.shop.dto.OrderDTO;
import com.example.online.grocery.shop.dto.OrderItemDTO;
import com.example.online.grocery.shop.dto.ProductDTO;
import com.example.online.grocery.shop.exception.InvalidCoordinatesPositionException;
import com.example.online.grocery.shop.exception.InvalidValueForProductNameException;
import com.example.online.grocery.shop.exception.NonExistentProductException;
import com.example.online.grocery.shop.exception.NonExistentProductLocationException;
import com.example.online.grocery.shop.model.Location;
import com.example.online.grocery.shop.model.Orders;
import com.example.online.grocery.shop.model.Products;
import com.example.online.grocery.shop.repository.LocationRepository;
import com.example.online.grocery.shop.repository.OrdersRepository;
import com.example.online.grocery.shop.repository.ProductsRepository;
import com.example.online.grocery.shop.utility.OnlineGroceryShopUtility;

import jakarta.transaction.Transactional;

@Service
public class OnlineGroceryShopService {

  private final ProductsRepository productsRepository;
  private final OrdersRepository ordersRepository;
  private final LocationRepository locationRepository;

  @Autowired
  public OnlineGroceryShopService(ProductsRepository productsRepository, OrdersRepository ordersRepository, LocationRepository locationRepository) {
    this.productsRepository = productsRepository;
    this.ordersRepository = ordersRepository;
    this.locationRepository = locationRepository;
  }

  public void createProduct(ProductDTO productDTO) {
    final String nameOfProduct = productDTO.getName();
    final Double productPrice = productDTO.getPrice();
    OnlineGroceryShopUtility.validateProductData(nameOfProduct, productPrice);
    Optional<Products> existingProduct = productsRepository.findByName(nameOfProduct);
    if (existingProduct.isPresent()) {
      throw new InvalidValueForProductNameException(OnlineGroceryShopUtility.ERROR_FOR_EXISTING_PRODUCT_RESPONSE);
    }
    final Products product = OnlineGroceryShopUtility.productToEntity(productDTO);
    final Integer quantity = productDTO.getQuantity();
    final Integer xPosition = productDTO.getXPosition();
    final Integer yPosition = productDTO.getYPosition();
    OnlineGroceryShopUtility.validateQuantity(quantity);
    OnlineGroceryShopUtility.validateCoordinates(xPosition, yPosition);
    Optional<Location> takenLocation = locationRepository.findByCoordinates(xPosition, yPosition);
    if (takenLocation.isPresent()) {
      throw new InvalidCoordinatesPositionException(OnlineGroceryShopUtility.ERROR_FOR_TAKEN_LOCATION_RESPONSE);
    }
    final Location location = OnlineGroceryShopUtility.locationToEntity(productDTO, product);
    productsRepository.save(product);
    locationRepository.save(location);
  }

  public Page<Products> listProducts(int pageNumber, int pageSize) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    return productsRepository.findAll(pageable);
  }

  @Transactional
  public void updateProduct(Long id, ProductDTO productDTO) {
    Products product = productsRepository.findById(id)
        .orElseThrow(() -> new NonExistentProductException(OnlineGroceryShopUtility.ERROR_FOR_NON_EXISTING_PRODUCT_RESPONSE));
    final String nameOfProduct = productDTO.getName();
    final Double productPrice = productDTO.getPrice();
    OnlineGroceryShopUtility.validateProductData(nameOfProduct, productPrice);
    Optional<Products> existingProduct = productsRepository.findByName(nameOfProduct);
    if (existingProduct.isPresent() && !existingProduct.get().getId().equals(id)) {
      throw new InvalidValueForProductNameException(OnlineGroceryShopUtility.ERROR_FOR_EXISTING_PRODUCT_RESPONSE);
    }
    product.setName(nameOfProduct);
    product.setPrice(productPrice);
    final Integer quantity = productDTO.getQuantity();
    final Integer xPosition = productDTO.getXPosition();
    final Integer yPosition = productDTO.getYPosition();
    OnlineGroceryShopUtility.validateQuantity(quantity);
    OnlineGroceryShopUtility.validateCoordinates(xPosition, yPosition);
    Optional<Location> takenLocation = locationRepository.findByCoordinates(xPosition, yPosition);
    if (takenLocation.isPresent() && !takenLocation.get().getId().equals(id)) {
      throw new InvalidCoordinatesPositionException(OnlineGroceryShopUtility.ERROR_FOR_TAKEN_LOCATION_RESPONSE);
    }
    Location location = locationRepository.findById(id)
        .orElseThrow(() -> new NonExistentProductLocationException(OnlineGroceryShopUtility.ERROR_FOR_NON_EXISTING_PRODUCT_LOCATION_RESPONSE));
    location.setQuantity(quantity);
    location.setXPosition(xPosition);
    location.setYPosition(yPosition);
  }

  public void deleteProduct(Long id) {
    Optional<Products> existingProduct = productsRepository.findById(id);
    if (!existingProduct.isPresent()) {
      throw new NonExistentProductException(OnlineGroceryShopUtility.ERROR_FOR_NON_EXISTING_PRODUCT_RESPONSE);
    }
    productsRepository.deleteById(id);
  }

  public void createOrder(OrderDTO orderDTO) {
    final List<OrderItemDTO> items = orderDTO.getItems();
    OnlineGroceryShopUtility.validateItems(items, productsRepository);
    final Orders order;
    // ordersRepository.save(order);
  }

  public String getOrderById(Long id) {
    return null;
  }

  public List<String> getRoutesFromOrder(Long id) {
    return Collections.emptyList();
  }
}
