package com.example.carmanagement.commons.data.request.order;

import com.example.carmanagement.commons.data.entity.BookingEntity;
import com.example.carmanagement.commons.data.request.accessory.AccessoryOrderRequest;
import com.example.carmanagement.commons.data.request.service.ServiceOrderRequest;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderRequest {
    @Min(value = 0, message = "totalPrice must greater 0")
    private Double totalPrice;
    @NotBlank(message = "currency is not null")
    private String currency;
    private String status;
    private List<ServiceOrderRequest> serviceOrder;
    private List<AccessoryOrderRequest> accessoryOrder;
    @NotNull(message = "customerId is not null")
    private Integer customerId;
    @NotNull(message = "bookingId is not null")
    private Integer bookingId;
}
