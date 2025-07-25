package com.example.online.grocery.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.online.grocery.shop.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

  @Query("SELECT l FROM Location l WHERE l.xPosition = ?1 AND l.yPosition = ?2")
  Optional<Location> findByCoordinates(Integer xPosition, Integer yPosition);
}
