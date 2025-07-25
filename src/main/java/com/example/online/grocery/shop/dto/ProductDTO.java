package com.example.online.grocery.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
  private String name;
  private Integer quantity;
  private Double price;

  @JsonProperty("xPosition")
  private Integer xPosition;

  @JsonProperty("yPosition")
  private Integer yPosition;
}
