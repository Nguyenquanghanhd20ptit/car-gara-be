package com.example.carmanagement.commons.data.request.orderMomo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class OrderMomoRequest {
    private Integer orderId;
    @NotBlank(message = "redirectUrl is not null")
    private String redirectUrl;
}
