package com.example.carmanagement.service.booking;

import com.example.carmanagement.commons.data.entity.AccessoryEntity;
import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.mapper.booking.BookingMapper;
import com.example.carmanagement.commons.data.request.SearchRequest;
import com.example.carmanagement.commons.data.response.PageResponse;
import com.example.carmanagement.commons.data.response.accessory.AccessoryResponse;
import com.example.carmanagement.commons.data.response.booking.BookingResponse;
import com.example.carmanagement.db.mysql.config.SpecificationConfig;
import com.example.carmanagement.db.mysql.repository.IBookingRepository;
import com.example.carmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingSearchService extends BaseService {
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private SpecificationConfig specificationConfig;
    public ResponseEntity<String> search(SearchRequest request){
        try {
            Specification<BookingEntity> spec = specificationConfig.buildSearch(request, BookingEntity.class);
            Pageable pageable =specificationConfig.buildPageable(request, BookingEntity.class);
            List<BookingEntity> bookingEntities = bookingRepository.findAll(spec,pageable).toList();
            Long count = bookingRepository.count(spec);
            List<BookingResponse> bookingResponses = bookingMapper.toResponses(bookingEntities);
            PageResponse<BookingResponse> pageResponse = new PageResponse<BookingResponse>()
                    .setItems(bookingResponses)
                    .setTotal(count);
            return createResponseSuccess(gsonDateFormat.toJson(pageResponse));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
