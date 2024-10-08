package com.example.carmanagement.service.customer;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.mapper.customer.CustomerMapper;
import com.example.carmanagement.commons.data.request.customer.CustomerRequest;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import com.example.carmanagement.db.mysql.repository.ICustomerRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class CustomerUpdateService extends BaseService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    public ResponseEntity<String> update(Integer id, CustomerRequest request){
        try{
            Optional<CustomerEntity> opt = customerRepository.findById(id);
            if(opt.isEmpty()){
                   return createResponseError(ERROR_CODE_NOT_INFORMATION,"Thông tin khac hang khong hợp lệ");
            }
            CustomerEntity customer = customerMapper.toEntity(request);
            customer.setId(id);
            CustomerEntity save = customerRepository.save(customer);
            if(save == null){
                return createResponseErrorDuringProcess();
            }
            return createResponseSuccess("update success");
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
