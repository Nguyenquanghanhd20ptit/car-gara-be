package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface  IOrderRepository extends JpaRepository<OrderEntity,Integer> , JpaSpecificationExecutor<OrderEntity> {
    @Modifying
    @Transactional
    @Query("update OrderEntity o set o.status = ?2 where  o.id = ?1")
     void updateStatusOrderById(Integer orderId,Integer status);

    @Modifying
    @Transactional
    @Query("update OrderEntity o set o.transactionName = ?2 where  o.id = ?1")
    void updateTransactionName(Integer orderId,String transactionName);

}
