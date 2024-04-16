package com.example.carmanagement.commons.data.response.customer;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CustomerResponse {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String avatarUrl;
    private LocalDateTime createdAt;
}
