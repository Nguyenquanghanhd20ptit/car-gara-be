package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.CarEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends JpaRepository<CarEntity,Integer> , JpaSpecificationExecutor<CarEntity> {

    @Modifying
    @Query("update CarEntity c set c.status = ?2 where c.id = ?1")
    void updateStatusCarIsSuccess(Integer carId,Integer status);
}
