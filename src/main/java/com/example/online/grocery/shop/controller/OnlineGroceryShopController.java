package com.example.online.grocery.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.online.grocery.shop.dto.OrderDTO;
import com.example.online.grocery.shop.dto.ProductDTO;
import com.example.online.grocery.shop.model.Products;
import com.example.online.grocery.shop.service.OnlineGroceryShopService;
import com.example.online.grocery.shop.utility.OnlineGroceryShopUtility;

import lombok.NonNull;

@RestController
@RequestMapping(path = "api/v1/grocery-shop")
public class OnlineGroceryShopController {

  private final OnlineGroceryShopService onlineGroceryShopService;

  @Autowired
  public OnlineGroceryShopController(OnlineGroceryShopService onlineGroceryShopService) {
    this.onlineGroceryShopService = onlineGroceryShopService;
  }

  @PostMapping(path = "/create-product")
  public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
    onlineGroceryShopService.createProduct(productDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(OnlineGroceryShopUtility.SUCCESSFULLY_CREATED_PRODUCT_RESPONSE);
  }

  // @formatter:off
  @GetMapping(path = "/list-products")
  public ResponseEntity<Page<Products>> listProducts(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "10") int pageSize) {
    return ResponseEntity.status(HttpStatus.OK).body(onlineGroceryShopService.listProducts(pageNumber, pageSize));
  }
  // @formatter:on

  @PutMapping(path = "/update-product/{id}")
  public ResponseEntity<String> updateProduct(@PathVariable("id") @NonNull Long id, @RequestBody ProductDTO productDTO) {
    onlineGroceryShopService.updateProduct(id, productDTO);
    return ResponseEntity.status(HttpStatus.OK).body(OnlineGroceryShopUtility.SUCCESSFULLY_UPDATED_PRODUCT_RESPONSE);
  }

  @DeleteMapping(path = "/delete-product/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable("id") @NonNull Long id) {
    onlineGroceryShopService.deleteProduct(id);
    return ResponseEntity.status(HttpStatus.OK).body(OnlineGroceryShopUtility.SUCCESSFULLY_REMOVED_PRODUCT_RESPONSE);
  }

  @PostMapping(path = "/create-order")
  public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
    onlineGroceryShopService.createOrder(orderDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(OnlineGroceryShopUtility.SUCCESSFULLY_CREATED_ORDER_RESPONSE);
  }

  @GetMapping(path = "/get-order/{id}")
  public ResponseEntity<String> getOrderById(@PathVariable("id") @NonNull Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(onlineGroceryShopService.getOrderById(id));
  }

  @GetMapping(path = "/get-routes/order/{id}")
  public ResponseEntity<List<String>> getRoutesFromOrder(@PathVariable("id") @NonNull Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(onlineGroceryShopService.getRoutesFromOrder(id));
  }
}
