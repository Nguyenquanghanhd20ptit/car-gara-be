package com.example.carmanagement.commons.data.mapper.service;


import com.example.carmanagement.commons.data.entity.ServiceEntity;
import com.example.carmanagement.commons.data.mapper.AbsMapper;
import com.example.carmanagement.commons.data.request.service.ServiceRequest;
import com.example.carmanagement.commons.data.response.service.ServiceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ServiceMapper extends AbsMapper<ServiceRequest, ServiceResponse, ServiceEntity> {
}
