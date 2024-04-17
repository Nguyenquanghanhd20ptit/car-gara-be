package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity,Integer>,
        JpaSpecificationExecutor<CustomerEntity> {
    @Query("SELECT c FROM CustomerEntity c WHERE c.email = ?1")
    List<CustomerEntity> findByEmail(String email);

    @Query("SELECT c FROM CustomerEntity c WHERE c.phoneNumber = ?1")
    List<CustomerEntity> findByPhoneNumber(String phoneNumber);


}
