package com.example.carmanagement.commons.data.mapper.service;

import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.entity.ServiceEntity;
import com.example.carmanagement.commons.data.entity.ServiceOrderEntity;
import com.example.carmanagement.commons.data.request.service.ServiceOrderRequest;
import com.example.carmanagement.commons.data.response.service.ServiceOrderResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ServiceOrderMapper  {
    @Autowired
    private ServiceMapper serviceMapper;
    public abstract ServiceOrderEntity toEntity(ServiceOrderRequest request, Integer orderId);
    @AfterMapping
    public void map(@MappingTarget ServiceOrderEntity entity, ServiceOrderRequest request, Integer orderId){
        entity.setService(new ServiceEntity().setId(request.getServiceId()));
        entity.setOrder(new OrderEntity().setId(orderId));
    }

    public List<ServiceOrderEntity> toEntities(List<ServiceOrderRequest> requests, Integer orderId) {
        if(ObjectUtils.isEmpty(requests)) return  null;
        return requests.stream()
                .map(request -> toEntity(request, orderId))
                .collect(Collectors.toList());
    }
    public abstract ServiceOrderResponse toResponse(ServiceOrderEntity entity);

    @AfterMapping
    public void map(@MappingTarget ServiceOrderResponse response,ServiceOrderEntity entity){
        response.setService(serviceMapper.toResponse(entity.getService()));
    }
    public abstract List<ServiceOrderResponse> toResponses(List<ServiceOrderEntity> entity);

}
