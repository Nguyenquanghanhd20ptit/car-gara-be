package com.example.carmanagement.commons.data.request.accessory;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccessoryOrderRequest {
    private Integer quantity;
    private Double totalPrice;
    private String currency;
    private Integer accessoryId;
    private Integer orderId;
}
