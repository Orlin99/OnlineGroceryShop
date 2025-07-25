package com.example.online.grocery.shop.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "RouteStep")
@Table(name = "route_steps")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RouteStep {

  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "route_id")
  private Routes route;

  @ManyToOne(optional = false)
  @JoinColumn(name = "location_id")
  private Location location;

  @Column(nullable = false)
  private Integer stepOrder;

  public RouteStep(Routes route, Location location, Integer stepOrder) {
    this.route = route;
    this.location = location;
    this.stepOrder = stepOrder;
  }
}
