package com.example.online.grocery.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.online.grocery.shop.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

  @Query("SELECT p FROM Products p WHERE p.name = ?1")
  Optional<Products> findByName(String name);
}
