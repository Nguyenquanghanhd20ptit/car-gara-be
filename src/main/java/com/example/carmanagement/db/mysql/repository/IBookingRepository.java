package com.example.carmanagement.db.mysql.repository;

import com.example.carmanagement.commons.data.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository extends JpaRepository<BookingEntity,Integer> , JpaSpecificationExecutor<BookingEntity> {

    @Modifying
    @Query("update BookingEntity b set b.status = ?2 where b.id = ?1")
    void updateStatusBookingIsSuccess(Integer bookingId,Integer status);
}
