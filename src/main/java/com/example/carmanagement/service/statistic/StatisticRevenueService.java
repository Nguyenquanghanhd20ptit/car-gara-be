package com.example.carmanagement.service.statistic;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.statistic.nevenue.StatisticRevenueDataResponse;
import com.example.carmanagement.commons.data.response.statistic.nevenue.StatisticRevenueResponse;
import com.example.carmanagement.db.mysql.config.SpecificationConfig;
import com.example.carmanagement.db.mysql.repository.IOrderRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class StatisticRevenueService extends BaseService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private SpecificationConfig specificationConfig;

    public ResponseEntity<String> statisticRevenue(SearchRequest request) {
        try {
            Specification<OrderEntity> spec = specificationConfig.buildSearch(request, OrderEntity.class);
            Pageable pageable = specificationConfig.buildPageable(request, OrderEntity.class);
            List<OrderEntity> orderEntities = orderRepository.findAll(spec, pageable).toList();
            AtomicReference<Double> totalRevenue = new AtomicReference<>(0.0);
            List<StatisticRevenueResponse> statisticRevenueResponses = orderEntities.stream().collect(Collectors.groupingBy(OrderEntity::getCustomer,
                            Collectors.collectingAndThen(Collectors.toList(),
                                    (List<OrderEntity> list) -> {
                                        CustomerEntity customer = list.get(0).getCustomer();
                                        Double totalPrice = 0.0;
                                        for (OrderEntity order : list) {
                                            totalPrice += order.getTotalPrice();
                                        }
                                        Double finalTotalPrice = totalPrice;
                                        totalRevenue.updateAndGet(v -> v + finalTotalPrice);
                                        StatisticRevenueResponse response = new StatisticRevenueResponse()
                                                .setCustomerId(customer.getId())
                                                .setName(customer.getName())
                                                .setEmail(customer.getEmail())
                                                .setPhoneNumber(customer.getPhoneNumber())
                                                .setCurrency(orderEntities.get(0).getCurrency())
                                                .setTotalPrice(totalPrice)
                                                .setNumCorrection(list.size());
                                        return response;

                                    }
                            ))).entrySet().stream().map(Map.Entry::getValue)
                    .collect(Collectors.toList());
            Long totalOrder = orderRepository.count(spec);
            List<OrderEntity> orders = orderRepository.findAll(spec);
            long totalCustomer = orders.stream()
                    .map(OrderEntity::getCustomer)
                    .distinct()
                    .count();
            StatisticRevenueDataResponse response = new StatisticRevenueDataResponse()
                    .setItems(statisticRevenueResponses)
                    .setTotalOrder(totalOrder)
                    .setTotalCustomer(totalCustomer)
                    .setTotalRevenue(totalRevenue.get());


            return createResponseSuccess(gsonDateFormat.toJson(response));
        } catch (Exception e) {
            return createResponseException(e);
        }
    }
}
