
package com.example.carmanagement.commons.data.response.accessory;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class AccessoryOrderResponse {
    private Integer id;
    private Integer quantity;
    private Double price;
    private String currency;
    private AccessoryResponse accessory;
    private LocalDateTime createdAt;
}
