package com.example.carmanagement.service.order;

import com.example.carmanagement.commons.data.constant.CarConstant;
import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.db.mysql.repository.IBookingRepository;
import com.example.carmanagement.db.mysql.repository.ICarRepository;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.BookingConstant.BOOKING_PAID_CODE;
import static com.example.carmanagement.commons.data.constant.OrderConstant.ORDER_PAID_CODE;
import static com.example.carmanagement.commons.data.constant.TransactionConstant.CASH_MESSAGE;
import static com.example.carmanagement.commons.data.constant.TransactionConstant.MOMO_MESSAGE;


@Service
public class UpdateStatusOrderIsSuccessService extends BaseService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private ICarRepository carRepository;
    @Transactional
    public ResponseEntity<String> updateStatus(Integer orderId){
        try {
            if(!validateReq(orderId)){
                return createResponseErrorValidate();
            }
            orderRepository.updateStatusOrderById(orderId,ORDER_PAID_CODE);
            orderRepository.updateTransactionName(orderId,CASH_MESSAGE);
            Optional<OrderEntity> orderOpt = orderRepository.findById(orderId);
            OrderEntity orderEntity = orderOpt.get();
            BookingEntity booking = orderEntity.getBooking();
            bookingRepository.updateStatusBookingIsSuccess(booking.getId(), BOOKING_PAID_CODE);
            carRepository.updateStatusCarIsSuccess(booking.getCar().getId(), CarConstant.CAR_RETURNED_CODE);
            return createResponseSuccess("update status order success");

        }catch (Exception e){
            return createResponseException(e);
        }
    }

    private boolean validateReq(Integer orderId) {
        try {
            Optional<OrderEntity> opt = orderRepository.findById(orderId);
            if (opt.isEmpty()) {
                this.invalidMessage = "orderId is invalid";
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
