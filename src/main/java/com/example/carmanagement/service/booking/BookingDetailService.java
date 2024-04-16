package com.example.carmanagement.service.booking;

import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.mapper.booking.BookingMapper;
import com.example.carmanagement.commons.data.response.booking.BookingResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import com.example.carmanagement.db.mysql.repository.IBookingRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.carmanagement.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;
import static com.example.carmanagement.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_NOT_INFORMATION;

@Service
public class BookingDetailService extends BaseService {
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private BookingMapper bookingMapper;
    public ResponseEntity<String> getById(Integer id){
        try {
            Optional<BookingEntity> opt = bookingRepository.findById(id);
            if(opt.isEmpty()){
                return  createResponseError(ERROR_CODE_NOT_INFORMATION,ERROR_MESSAGE_NOT_INFORMATION);
            }
            BookingResponse response = bookingMapper.toResponse(opt.get());
            return createResponseSuccess(gsonDateFormat.toJson(response));

        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
