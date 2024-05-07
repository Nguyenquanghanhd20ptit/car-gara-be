package com.example.carmanagement.service.statistic;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.mapper.customer.CustomerMapper;
import com.example.carmanagement.commons.data.mapper.order.OrderMapper;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerOrderResponse;
import com.example.carmanagement.commons.data.response.order.OrderResponse;
import com.example.carmanagement.db.mysql.config.SpecificationConfig;
import com.example.carmanagement.db.mysql.repository.ICustomerRepository;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_REQUEST_INVALID_DATA;

@Service
public class CustomerRevenueDetailService extends BaseService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private SpecificationConfig specificationConfig;
    public ResponseEntity<String> getDetail(Integer customerId,SearchRequest request){
        try {
            Optional<CustomerEntity> byId = customerRepository.findById(customerId);
            if(byId.isEmpty()){
                return createResponseError(ERROR_CODE_REQUEST_INVALID_DATA,"customerId is not exists");
            }

            Specification<OrderEntity> spec = specificationConfig.buildSearch(request, OrderEntity.class);
            Pageable pageable =specificationConfig.buildPageable(request, OrderEntity.class);
            List<OrderEntity> orderEntities = orderRepository.findAll(spec,pageable).toList();
            Long count = orderRepository.count(spec);
            List<OrderResponse> responses = orderMapper.toResponses(orderEntities);

            PageResponse<OrderResponse> pageResponse = new PageResponse<OrderResponse>()
                    .setItems(responses)
                    .setTotal(count);
            return createResponseSuccess(gsonDateFormat.toJson(pageResponse));
        }catch (Exception e){
            return  createResponseException(e);
        }
    }
}
