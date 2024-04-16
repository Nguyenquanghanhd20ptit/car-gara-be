package com.example.carmanagement.service.car;

import com.example.carmanagement.commons.data.entity.CarEntity;
import com.example.carmanagement.commons.data.mapper.car.CarMapper;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.car.CarResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import com.example.carmanagement.db.mysql.config.SpecificationConfig;
import com.example.carmanagement.db.mysql.repository.ICarRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarSearchService extends BaseService {
    @Autowired
    private ICarRepository carRepository;
    @Autowired
    private SpecificationConfig specificationConfig;
    @Autowired
    private CarMapper carMapper;

    public ResponseEntity<String> search(SearchRequest request){
        try {
            Specification<CarEntity> spec = specificationConfig.buildSearch(request, CarEntity.class);
            Pageable pageable =specificationConfig.buildPageable(request,CarEntity.class);
            List<CarEntity>  carEntities = carRepository.findAll(spec,pageable).toList();
            Long count = carRepository.count(spec);
            List<CarResponse> carResponses = carMapper.toCarResponses(carEntities);
            PageResponse<CarResponse> pageResponse = new PageResponse<CarResponse>()
                    .setItems(carResponses)
                    .setTotal(count);
            return createResponseSuccess(gsonDateFormat.toJson(pageResponse));
        }catch (Exception e){
            return  createResponseException(e);
        }
    }
}
