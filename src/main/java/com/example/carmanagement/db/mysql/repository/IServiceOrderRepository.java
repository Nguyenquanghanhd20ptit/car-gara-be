package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.ServiceOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceOrderRepository extends JpaRepository<ServiceOrderEntity,Integer> {
}
