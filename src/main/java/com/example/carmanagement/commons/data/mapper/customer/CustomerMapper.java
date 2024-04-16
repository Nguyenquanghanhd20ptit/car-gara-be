package com.example.carmanagement.commons.data.mapper.customer;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.mapper.AbsMapper;
import com.example.carmanagement.commons.data.request.customer.CustomerRequest;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper extends AbsMapper<CustomerRequest, CustomerResponse, CustomerEntity> {
}
