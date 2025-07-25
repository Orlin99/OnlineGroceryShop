package com.example.online.grocery.shop.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Routes")
@Table(name = "routes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Routes {

  @Id
  @SequenceGenerator(name = "routes_sequence", sequenceName = "routes_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routes_sequence")
  private Long id;

  @OneToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Orders order;

  @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RouteStep> steps;
}
