package com.example.carmanagement.commons.data.response.service;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ServiceResponse {
    private Integer id;
    private String name;
    private String type;
    private Double price;
    private String currency;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;
}
