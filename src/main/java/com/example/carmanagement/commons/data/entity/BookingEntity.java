package com.example.carmanagement.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String carNameSearch;
    private String customerNameSearch;
    private String slotName;
    private String conditionDes;
    private String made;
    private Integer status;
    private LocalDateTime appointmentTime;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne(targetEntity = CustomerEntity.class)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    @ManyToOne(targetEntity = CarEntity.class)
    @JoinColumn(name = "car_id")
    private CarEntity car;
}
