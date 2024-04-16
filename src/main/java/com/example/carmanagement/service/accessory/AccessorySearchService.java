package com.example.carmanagement.service.accessory;

import com.example.carmanagement.commons.data.entity.AccessoryEntity;
import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.mapper.accessory.AccessoryMapper;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.accessory.AccessoryResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import com.example.carmanagement.db.mysql.config.SpecificationConfig;
import com.example.carmanagement.db.mysql.repository.IAccessoryRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccessorySearchService extends BaseService {
    @Autowired
    private IAccessoryRepository accessoryRepository;
    @Autowired
    private SpecificationConfig specificationConfig;
    @Autowired
    private AccessoryMapper accessoryMapper;
    public ResponseEntity<String> searchAccessory(SearchRequest request){
        try {
            Specification<AccessoryEntity> spec = specificationConfig.buildSearch(request, AccessoryEntity.class);
            Pageable pageable =specificationConfig.buildPageable(request,CustomerEntity.class);
            List<AccessoryEntity>  accessoryEntities = accessoryRepository.findAll(spec,pageable).toList();
            Long count = accessoryRepository.count(spec);
            List<AccessoryResponse> accessoryResponses = accessoryMapper.toResponses(accessoryEntities);
            PageResponse<AccessoryResponse> pageResponse = new PageResponse<AccessoryResponse>()
                    .setItems(accessoryResponses)
                    .setTotal(count);
            return createResponseSuccess(gsonDateFormat.toJson(pageResponse));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
