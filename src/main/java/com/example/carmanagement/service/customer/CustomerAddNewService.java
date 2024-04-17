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
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerAddNewService extends BaseService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ICustomerRepository customerRepository;
    public ResponseEntity<String> addNew(CustomerRequest request){
        try {
            List<CustomerEntity> checkEmail = customerRepository.findByEmail(request.getEmail());
            List<CustomerEntity> checkPhone  = customerRepository.findByPhoneNumber(request.getPhoneNumber());

            if (!checkEmail.isEmpty()) {
                this.invalidMessage = "Email đã tồn tại";
                return createResponseErrorValidate();
            }

            if (!checkPhone.isEmpty()) {
                this.invalidMessage = "Phone đã tồn tại";
                return createResponseErrorValidate();
            }

            CustomerEntity entity = customerMapper.toEntity(request);
            Optional<CustomerEntity> opt = Optional.of(customerRepository.save(entity));
            if(!opt.isPresent()){
                return createResponseErrorDuringProcess();
            }
            return createResponseSuccess("Add success");
        }catch (Exception e){
            e.printStackTrace();
           return createResponseException(e);
        }
    }
}
