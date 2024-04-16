package com.example.carmanagement.commons.data.response.accessory;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class AccessoryResponse {
    private Integer id;
    private String name;
    private Double price;
    private String currency;
    private Integer quantity;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;
}
