package com.example.carmanagement.service.service;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.entity.ServiceEntity;
import com.example.carmanagement.commons.data.mapper.service.ServiceMapper;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import com.example.carmanagement.commons.data.response.service.ServiceResponse;
import com.example.carmanagement.db.mysql.config.SpecificationConfig;
import com.example.carmanagement.db.mysql.repository.IServiceRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;
import static com.example.carmanagement.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_NOT_INFORMATION;

@Service
public class ServiceSearchService extends BaseService {
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private SpecificationConfig specificationConfig;
    @Autowired
    private ServiceMapper serviceMapper;
    public ResponseEntity<String> searchService(SearchRequest request){
        try {
            Specification<ServiceEntity> spec = specificationConfig.buildSearch(request, ServiceEntity.class);
            Pageable pageable =specificationConfig.buildPageable(request,ServiceEntity.class);
            List<ServiceEntity> serviceEntities = serviceRepository.findAll(spec,pageable).toList();
            Long count = serviceRepository.count(spec);
            List<ServiceResponse> serviceResponses = serviceMapper.toResponses(serviceEntities);
            PageResponse<ServiceResponse> pageResponse = new PageResponse<ServiceResponse>()
                    .setItems(serviceResponses)
                    .setTotal(count);
            return createResponseSuccess(gsonDateFormat.toJson(pageResponse));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
