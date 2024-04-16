package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity,Integer>,
        JpaSpecificationExecutor<CustomerEntity> {
}
