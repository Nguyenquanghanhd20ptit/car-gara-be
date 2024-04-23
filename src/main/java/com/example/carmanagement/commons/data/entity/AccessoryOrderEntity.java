package com.example.carmanagement.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "accessory_order")
public class AccessoryOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private Double price;
    private String currency;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = AccessoryEntity.class)
    @JoinColumn(name = "accessory_id")
    private AccessoryEntity accessory;

    @ManyToOne(targetEntity = OrderEntity.class)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
