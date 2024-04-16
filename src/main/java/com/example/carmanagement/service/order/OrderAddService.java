package com.example.carmanagement.service.order;

import com.example.carmanagement.commons.data.entity.*;
import com.example.carmanagement.commons.data.mapper.accessory.AccessoryOrderMapper;
import com.example.carmanagement.commons.data.mapper.order.OrderMapper;
import com.example.carmanagement.commons.data.mapper.service.ServiceOrderMapper;
import com.example.carmanagement.commons.data.model.enums.Currency;
import com.example.carmanagement.commons.data.request.accessory.AccessoryOrderRequest;
import com.example.carmanagement.commons.data.request.order.OrderRequest;
import com.example.carmanagement.commons.data.request.service.ServiceOrderRequest;
import com.example.carmanagement.db.mysql.repository.*;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.carmanagement.utils.CollectionUtils.collectToMap;
import static com.example.carmanagement.utils.CollectionUtils.extractFieldToSet;

@Service
public class OrderAddService extends BaseService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ServiceOrderMapper serviceOrderMapper;
    @Autowired
    private AccessoryOrderMapper accessoryOrderMapper;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private IAccessoryRepository accessoryRepository;
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private IAccessoryOrderRepository accessoryOrderRepository;
    @Autowired
    private IServiceOrderRepository serviceOrderRepository;
    @Transactional
    public ResponseEntity<String> addNew(OrderRequest orderRequest){
        try {
            if( validateReq(orderRequest)){
                return createResponseErrorValidate();
            }
            OrderEntity order = orderMapper.toEntity(orderRequest);
            OrderEntity orderReturn = orderRepository.save(order);
            if(ObjectUtils.isEmpty(orderReturn)){
                return createResponseErrorDuringProcess();
            }
            List<AccessoryOrderEntity> accessoryEntities = accessoryOrderMapper.toEntities(orderRequest.getAccessoryOrder(),orderReturn.getId());
            List<ServiceOrderEntity> serviceOrderEntities = serviceOrderMapper.toEntities(orderRequest.getServiceOrder(), orderReturn.getId());
            if(!ObjectUtils.isEmpty(accessoryEntities)) accessoryOrderRepository.saveAll(accessoryEntities);
            if(!ObjectUtils.isEmpty(serviceOrderEntities)) serviceOrderRepository.saveAll(serviceOrderEntities);
            return createResponseSuccess(orderReturn.getId().toString());
        }catch (Exception e){
            e.printStackTrace();
            return createResponseException(e);
        }
    }

    private Boolean validateReq(OrderRequest orderRequest) {
        try {
            Optional<CustomerEntity> optionalCustomer = customerRepository.findById(orderRequest.getCustomerId());
            if(optionalCustomer.isEmpty()){
                this.invalidMessage =  "customerId is invalid";
                return false;
            }
            Optional<BookingEntity> optionalBooking = bookingRepository.findById(orderRequest.getBookingId());
            if(optionalBooking.isEmpty()){
                this.invalidMessage =  "Booking is invalid";
                return false;
            }
            if(ObjectUtils.isEmpty(orderRequest.getServiceOrder()) && ObjectUtils.isEmpty(orderRequest.getAccessoryOrder())){
                return false;
            }
            if (!orderRequest.getCurrency().equalsIgnoreCase(Currency.USD.toString()) &&
                    !orderRequest.getCurrency().equalsIgnoreCase(Currency.VNĐ.toString())) {
                this.invalidMessage = "currency is invalid";
                return false;
            }
            if (orderRequest.getCurrency().equals(Currency.VNĐ.toString())
                    && ( orderRequest.getTotalPrice() != Math.floor(orderRequest.getTotalPrice()) || orderRequest.getTotalPrice() < 1000)) {
                this.invalidMessage = "VND money is a decimal number or less than 1000";
                return false;
            }
            AtomicReference<Double> totalPriceReality  = new AtomicReference<>(0.0);
            if(!ObjectUtils.isEmpty( orderRequest.getAccessoryOrder())){
                List<AccessoryOrderRequest> accessoryOrderRequest = orderRequest.getAccessoryOrder();
                Set<Integer> accessoryIds = extractFieldToSet( accessoryOrderRequest,AccessoryOrderRequest::getAccessoryId);
                Map<Integer,AccessoryOrderRequest> accessoryOrderMap = collectToMap(accessoryOrderRequest,AccessoryOrderRequest::getAccessoryId);
                List<AccessoryEntity> accessoryEntities = accessoryRepository.findAllById(accessoryIds);
                accessoryEntities.stream().forEach(accessoryEntity -> {
                    totalPriceReality.updateAndGet(v -> v + accessoryEntity.getPrice() *
                            accessoryOrderMap.get(accessoryEntity.getId()).getQuantity());
                });
            }
            if(!ObjectUtils.isEmpty( orderRequest.getServiceOrder())){
                List<ServiceOrderRequest> serviceOrderRequests = orderRequest.getServiceOrder();
                Set<Integer> serviceIds = extractFieldToSet( serviceOrderRequests,ServiceOrderRequest::getServiceId);
                List<ServiceEntity> serviceEntities = serviceRepository.findAllById(serviceIds);
                serviceEntities.stream().forEach(serviceEntity -> {
                    totalPriceReality.updateAndGet(v -> v + serviceEntity.getPrice());
                });
            }
            if(totalPriceReality.get().equals(orderRequest.getTotalPrice())){
                this.invalidMessage = "order is invalid";
                return false;
            }
            return true;
        }catch (Exception e){
            return  false;
        }

    }
}
