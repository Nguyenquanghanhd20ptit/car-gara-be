package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITransactionTypeRepository extends JpaRepository<TransactionTypeEntity,Integer> {
    @Query("select t from TransactionTypeEntity t where t.order.id = ?1")
    List<TransactionTypeEntity> findByOrderId(Integer orderId);
}
