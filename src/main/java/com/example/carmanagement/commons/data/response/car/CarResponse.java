package com.example.carmanagement.commons.data.response.car;

import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CarResponse {
    private Integer id;
    private String name;
    private String licensePlates;
    private String status;
    private String imageUrl;
    private CustomerResponse customer;
    private LocalDateTime createdAt;
}
