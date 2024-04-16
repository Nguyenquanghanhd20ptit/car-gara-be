package com.example.carmanagement.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "service_order")
public class ServiceOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double totalPrice;
    private String currency;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = ServiceEntity.class)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne(targetEntity = OrderEntity.class)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
