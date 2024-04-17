package com.example.carmanagement.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors
@Entity
@Table(name = "transaction_type")
public class TransactionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tranTypeId;

    @ManyToOne(targetEntity = OrderEntity.class)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(targetEntity = TransactionEntity.class)
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;
}
