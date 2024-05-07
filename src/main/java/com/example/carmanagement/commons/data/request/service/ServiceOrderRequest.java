package com.example.carmanagement.commons.data.request.service;
import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class ServiceOrderRequest {
    private Double price;
    private String currency;
    private Integer serviceId;
    private Integer orderId;
}
