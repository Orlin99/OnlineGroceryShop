package com.example.online.grocery.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.online.grocery.shop.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
  // Default Empty Implementation
}
