package com.example.carmanagement.commons.data.request.order;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatusOrderRequest {
    private String orderId;
    private Integer transactionId;
    private Integer statusCode;
}
