package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.AccessoryOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccessoryOrderRepository extends JpaRepository<AccessoryOrderEntity, Integer> {
}
