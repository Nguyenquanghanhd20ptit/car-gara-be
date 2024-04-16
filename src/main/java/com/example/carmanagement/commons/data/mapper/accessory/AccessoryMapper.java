package com.example.carmanagement.commons.data.mapper.accessory;

import com.example.carmanagement.commons.data.entity.AccessoryEntity;
import com.example.carmanagement.commons.data.mapper.AbsMapper;
import com.example.carmanagement.commons.data.request.accessory.AccessoryRequest;
import com.example.carmanagement.commons.data.response.accessory.AccessoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AccessoryMapper extends AbsMapper<AccessoryRequest, AccessoryResponse, AccessoryEntity> {

}
