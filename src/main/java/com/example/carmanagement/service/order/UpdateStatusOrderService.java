package com.example.carmanagement.service.order;

import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.request.order.StatusOrderRequest;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.OrderConstant.ORDER_STATUS;

@Service
public class UpdateStatusOrderService extends BaseService {
    @Autowired
    private IOrderRepository orderRepository;
    public ResponseEntity<String> updateStatus(StatusOrderRequest request){
        try {
            if(!validateReq(request)){
                return createResponseErrorValidate();
            }
            orderRepository.updateOrderById(request.getOrderId(),request.getStatus());
            return createResponseSuccess("update status order success");

        }catch (Exception e){
            return createResponseException(e);
        }
    }

    private boolean validateReq(StatusOrderRequest request) {
        try {
            Optional<OrderEntity> opt = orderRepository.findById(request.getOrderId());
            if (opt.isEmpty()) {
                this.invalidMessage = "orderId is invalid";
                return false;

            }
            if (ORDER_STATUS.containsKey(request.getStatus())) {
                this.invalidMessage = "status is invalid";
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
