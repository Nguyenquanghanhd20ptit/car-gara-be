package com.example.carmanagement.commons.data.response.customer;

import com.example.carmanagement.commons.data.response.order.OrderResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class CustomerOrderResponse {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String avatarUrl;
    private List<OrderResponse> orders;
    private LocalDateTime createdAt;
}
