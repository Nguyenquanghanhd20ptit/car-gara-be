package com.example.carmanagement.commons.data.mapper.customer;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.mapper.AbsMapper;
import com.example.carmanagement.commons.data.mapper.order.OrderMapper;
import com.example.carmanagement.commons.data.request.customer.CustomerRequest;
import com.example.carmanagement.commons.data.response.customer.CustomerOrderResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper extends AbsMapper<CustomerRequest, CustomerResponse, CustomerEntity> {
}
