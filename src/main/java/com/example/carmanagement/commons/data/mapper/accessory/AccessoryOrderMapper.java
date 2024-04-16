package com.example.carmanagement.commons.data.mapper.accessory;

import com.example.carmanagement.commons.data.entity.AccessoryEntity;
import com.example.carmanagement.commons.data.entity.AccessoryOrderEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.request.accessory.AccessoryOrderRequest;
import com.example.carmanagement.commons.data.response.accessory.AccessoryOrderResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AccessoryOrderMapper {
    @Autowired
    private AccessoryMapper accessoryMapper;
    public abstract AccessoryOrderEntity toEntity(AccessoryOrderRequest request, Integer orderId);
    @AfterMapping
    public void map(@MappingTarget AccessoryOrderEntity entity, AccessoryOrderRequest request, Integer orderId){
        entity.setAccessory(new AccessoryEntity().setId(request.getAccessoryId()));
        entity.setOrder(new OrderEntity().setId(orderId));
    }

    public  List<AccessoryOrderEntity> toEntities(List<AccessoryOrderRequest> requests, Integer orderId ){
        if(ObjectUtils.isEmpty(requests)) return  null;
        return requests.stream().map(accessoryOrderRequest -> toEntity(accessoryOrderRequest,orderId))
                .collect(Collectors.toList());
    }
    public abstract AccessoryOrderResponse toResponse(AccessoryOrderEntity entity);

    @AfterMapping
    public void map(@MappingTarget AccessoryOrderResponse response,AccessoryOrderEntity entity){
        response.setAccessory(accessoryMapper.toResponse(entity.getAccessory()));
    }
    public abstract List<AccessoryOrderResponse> toResponses(List<AccessoryOrderEntity> entity);
}
