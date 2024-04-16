package com.example.carmanagement.commons.data.response.service;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ServiceOrderResponse {
    private Integer id;
    private Double totalPrice;
    private String currency;
    private ServiceResponse service;
    private LocalDateTime createdAt;
}
