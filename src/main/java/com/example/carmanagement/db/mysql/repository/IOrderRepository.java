package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  IOrderRepository extends JpaRepository<OrderEntity,Integer> , JpaSpecificationExecutor<OrderEntity> {
    @Modifying
    @Query("update OrderEntity o set o.status = ?2 where  o.id = ?1")
     void updateOrderById(Integer orderId,Integer status);
}
