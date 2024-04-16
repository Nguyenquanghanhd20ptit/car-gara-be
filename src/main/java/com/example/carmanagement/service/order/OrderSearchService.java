package com.example.carmanagement.service.order;

import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.mapper.order.OrderMapper;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.booking.BookingResponse;
import com.example.carmanagement.commons.data.response.order.OrderResponse;
import com.example.carmanagement.db.mysql.config.SpecificationConfig;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSearchService extends BaseService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private SpecificationConfig specificationConfig;
    @Autowired
    private OrderMapper orderMapper;
    public ResponseEntity<String> search(SearchRequest request){
        try {
            Specification<OrderEntity> spec = specificationConfig.buildSearch(request, OrderEntity.class);
            Pageable pageable =specificationConfig.buildPageable(request, OrderEntity.class);
            List<OrderEntity> orderEntities = orderRepository.findAll(spec,pageable).toList();
            Long count = orderRepository.count(spec);
            List<OrderResponse> orderResponses = orderMapper.toResponses(orderEntities);
            PageResponse<OrderResponse> pageResponse = new PageResponse<OrderResponse>()
                    .setItems(orderResponses)
                    .setTotal(count);
            return createResponseSuccess(gsonDateFormat.toJson(pageResponse));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
