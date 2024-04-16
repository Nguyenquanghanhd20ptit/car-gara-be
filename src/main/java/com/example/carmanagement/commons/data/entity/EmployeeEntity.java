package com.example.carmanagement.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String position;
    private String email;
    private String address;
    private String phoneNumber;
    private String avatarUrl;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
