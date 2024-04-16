package com.example.carmanagement.service.customer;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.mapper.customer.CustomerMapper;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import com.example.carmanagement.db.mysql.config.SpecificationConfig;
import com.example.carmanagement.db.mysql.repository.ICustomerRepository;
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
public class CustomerSearchService extends BaseService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private SpecificationConfig specificationConfig;
    @Autowired
    private CustomerMapper customerMapper;
    public ResponseEntity<String> searchCustomer(SearchRequest request){
        try {
            Specification<CustomerEntity> spec = specificationConfig.buildSearch(request, CustomerEntity.class);
            Pageable pageable =specificationConfig.buildPageable(request,CustomerEntity.class);
            List<CustomerEntity> customerEntities = customerRepository.findAll(spec,pageable).toList();
            Long count = customerRepository.count(spec);
            List<CustomerResponse> customerResponses = customerMapper.toResponses(customerEntities);
            PageResponse<CustomerResponse> pageResponse = new PageResponse<CustomerResponse>()
                    .setItems(customerResponses)
                    .setTotal(count);
            return createResponseSuccess(gsonDateFormat.toJson(pageResponse));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
