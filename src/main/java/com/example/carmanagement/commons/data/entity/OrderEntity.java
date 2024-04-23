package com.example.carmanagement.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String currency;
    private String transactionName;
    private Integer status;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order")
    private List<ServiceOrderEntity> serviceOrder;

    @OneToMany(mappedBy = "order")
    private List<AccessoryOrderEntity> accessoryOrder;

    @ManyToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(targetEntity = BookingEntity.class)
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;
}
