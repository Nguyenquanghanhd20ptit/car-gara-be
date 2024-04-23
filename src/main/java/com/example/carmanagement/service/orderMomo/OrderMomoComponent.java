package com.example.carmanagement.service.orderMomo;

import com.example.carmanagement.commons.data.constant.CarConstant;
import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.request.order.StatusOrderRequest;
import com.example.carmanagement.db.mysql.repository.IBookingRepository;
import com.example.carmanagement.db.mysql.repository.ICarRepository;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.db.redis.repository.order.IStatusOrderRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.BookingConstant.BOOKING_PAID_CODE;
import static com.example.carmanagement.commons.data.constant.OrderConstant.ORDER_PAID_CODE;
import static com.example.carmanagement.commons.data.constant.OrderConstant.ORDER_WAIT_PAYMENT_CODE;

@Component
public class OrderMomoComponent {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IStatusOrderRedis statusOrderRedis;
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private ICarRepository carRepository;
    @Transactional
    public Boolean updateStatusOrder(StatusOrderRequest request){
        try {
            orderRepository.updateStatusOrderById(Integer.parseInt( request.getOrderId()),request.getStatusCode());
            if(request.getStatusCode().equals(ORDER_PAID_CODE)){
                Optional<OrderEntity> orderOpt = orderRepository.findById(Integer.parseInt( request.getOrderId()));
                OrderEntity orderEntity = orderOpt.get();
                BookingEntity booking = orderEntity.getBooking();
                bookingRepository.updateStatusBookingIsSuccess(booking.getId(), BOOKING_PAID_CODE);
                carRepository.updateStatusCarIsSuccess(booking.getCar().getId(), CarConstant.CAR_RETURNED_CODE);
                return true;
            }
            if(request.getStatusCode().equals(ORDER_WAIT_PAYMENT_CODE)){
                statusOrderRedis.deleteData(request.getOrderId());
                statusOrderRedis.pushData(request.getOrderId(),request);
                return true;
            } else {
                statusOrderRedis.deleteData(request.getOrderId());
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
