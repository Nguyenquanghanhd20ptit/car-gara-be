package com.example.carmanagement.commons.data.mapper.order;

import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.mapper.accessory.AccessoryOrderMapper;
import com.example.carmanagement.commons.data.mapper.booking.BookingMapper;
import com.example.carmanagement.commons.data.mapper.customer.CustomerMapper;
import com.example.carmanagement.commons.data.mapper.service.ServiceOrderMapper;
import com.example.carmanagement.commons.data.request.order.OrderRequest;
import com.example.carmanagement.commons.data.response.order.OrderResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.carmanagement.commons.data.constant.OrderConstant.ORDER_STATUS;
import static com.example.carmanagement.commons.data.constant.OrderConstant.UNPAID_CODE;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    private AccessoryOrderMapper accessoryOrderMapper;
    @Autowired
    private ServiceOrderMapper serviceOrderMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private BookingMapper bookingMapper;

    public abstract OrderEntity toEntity(OrderRequest request);
    @AfterMapping
    public void  map(@MappingTarget OrderEntity order, OrderRequest request){
        order.setStatus(UNPAID_CODE);
        order.setCustomer(new CustomerEntity().setId(request.getCustomerId()));
        order.setBooking(new BookingEntity().setId(request.getBookingId()));
    }
    public  abstract  OrderResponse toResponse(OrderEntity order);
    @AfterMapping
    public void  map(@MappingTarget OrderResponse orderResponse,OrderEntity order){
        orderResponse.setStatus(ORDER_STATUS.get(order.getStatus()));
        orderResponse.setServiceOrders(serviceOrderMapper.toResponses(order.getServiceOrder()));
        orderResponse.setAccessoryOrders(accessoryOrderMapper.toResponses(order.getAccessoryOrder()));
        orderResponse.setCustomer(customerMapper.toResponse(order.getCustomer()));
        orderResponse.setBooking(bookingMapper.toResponse(order.getBooking()));
    }
    public abstract List<OrderResponse> toResponses(List<OrderEntity> orderEntities);
}
