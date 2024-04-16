package com.example.carmanagement.service.customer;

import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.db.mysql.repository.ICustomerRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class CustomerDeleteService extends BaseService {
    @Autowired
    private ICustomerRepository customerRepository;
    public ResponseEntity<String> deleteById(Integer customerId){
        try {
            Optional<CustomerEntity> opt = customerRepository.findById(customerId);
            if(!opt.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Thông tin khac hang khong hợp lệ");
            }
            customerRepository.deleteById(customerId);
            return createResponseSuccess("Xóa khac hang thành công");
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
