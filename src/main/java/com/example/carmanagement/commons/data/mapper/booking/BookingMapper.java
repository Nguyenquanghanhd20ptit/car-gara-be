package com.example.carmanagement.commons.data.mapper.booking;

import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.mapper.car.CarMapper;
import com.example.carmanagement.commons.data.mapper.customer.CustomerMapper;
import com.example.carmanagement.commons.data.response.booking.BookingResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.carmanagement.commons.data.constant.BookingConstant.BOOKING_STATUS;

@Mapper(componentModel = "spring")
public  abstract class BookingMapper {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CarMapper carMapper;

    @Mapping(target = "customer" ,ignore = true)
    @Mapping(target = "car", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract BookingResponse toResponse(BookingEntity booking);

    @AfterMapping
    public void map(@MappingTarget  BookingResponse bookingResponse, BookingEntity booking){
        bookingResponse.setCustomer(customerMapper.toResponse(booking.getCustomer()));
        bookingResponse.setCar(carMapper.toCarResponse(booking.getCar()));
        bookingResponse.setStatus(BOOKING_STATUS.get(booking.getStatus()));
    }
    public abstract List<BookingResponse> toResponses(List<BookingEntity> bookingEntities);
}
