package com.example.online.grocery.shop.model;

import java.util.List;

import com.example.online.grocery.shop.utility.StatusAttributeConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Orders")
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Orders {

  @Id
  @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
  private Long id;

  @Column(name = "status", nullable = false, columnDefinition = "TEXT")
  @Convert(converter = StatusAttributeConverter.class)
  private Status status;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items;

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
  private Routes route;
}
