package com.example.carmanagement.service.order;

import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.mapper.order.OrderMapper;
import com.example.carmanagement.commons.data.response.booking.BookingResponse;
import com.example.carmanagement.commons.data.response.order.OrderResponse;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;
import static com.example.carmanagement.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_NOT_INFORMATION;

@Service
public class OrderDetailService  extends BaseService {

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    public ResponseEntity<String> getById(Integer id){
        try {
            Optional<OrderEntity> opt = orderRepository.findById(id);
            if(opt.isEmpty()){
                return  createResponseError(ERROR_CODE_NOT_INFORMATION,ERROR_MESSAGE_NOT_INFORMATION);
            }
            OrderResponse response = orderMapper.toResponse(opt.get());
            return createResponseSuccess(gsonDateFormat.toJson(response));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
