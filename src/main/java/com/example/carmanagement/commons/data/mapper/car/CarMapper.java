package com.example.carmanagement.commons.data.mapper.car;

import com.example.carmanagement.commons.data.entity.CarEntity;
import com.example.carmanagement.commons.data.mapper.customer.CustomerMapper;
import com.example.carmanagement.commons.data.response.car.CarResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.carmanagement.commons.data.constant.CarConstant.CAR_STATUS;

@Mapper(componentModel = "spring")
public abstract class CarMapper {
    @Autowired
    private CustomerMapper customerMapper;
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract CarResponse toCarResponse(CarEntity car);
    @AfterMapping
    public  void map(@MappingTarget CarResponse carResponse, CarEntity car){
        carResponse.setCustomer(customerMapper.toResponse(car.getCustomer()));
        carResponse.setStatus(CAR_STATUS.get(car.getStatus()));
    }

    public abstract  List<CarResponse> toCarResponses(List<CarEntity> carEntities);
}
