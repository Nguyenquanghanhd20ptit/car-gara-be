package com.example.carmanagement.commons.data.request.order;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatusOrderRequest {
    public Integer orderId;
    public Integer status;
}
