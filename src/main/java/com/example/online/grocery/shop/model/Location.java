package com.example.online.grocery.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Location")
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Location {

  @Id
  @SequenceGenerator(name = "location_sequence", sequenceName = "location_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_sequence")
  private Long id;

  @Column(nullable = false)
  private Integer xPosition;

  @Column(nullable = false)
  private Integer yPosition;

  @Column(nullable = false)
  private Integer quantity;

  @JsonBackReference
  @OneToOne(optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Products product;
}
