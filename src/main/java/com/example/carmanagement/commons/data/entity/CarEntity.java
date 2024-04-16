package com.example.carmanagement.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String licensePlates;
    private Integer status;
    private String imageUrl;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
