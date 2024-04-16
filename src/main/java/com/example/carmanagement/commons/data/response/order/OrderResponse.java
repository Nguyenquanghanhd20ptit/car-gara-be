package com.example.carmanagement.commons.data.response.order;

import com.example.carmanagement.commons.data.response.accessory.AccessoryOrderResponse;
import com.example.carmanagement.commons.data.response.booking.BookingResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import com.example.carmanagement.commons.data.response.service.ServiceOrderResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderResponse {
    private Integer id;
    private Double totalPrice;
    private String currency;
    private String status;
    private List<ServiceOrderResponse> serviceOrders;
    private List<AccessoryOrderResponse> accessoryOrders;
    private CustomerResponse customer;
    private BookingResponse booking;
    private LocalDateTime createdAt;
}
