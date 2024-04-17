package com.example.carmanagement.commons.data.response.booking;

import com.example.carmanagement.commons.data.response.car.CarResponse;
import com.example.carmanagement.commons.data.response.customer.CustomerResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BookingResponse {
    private Integer id;
    private String name;
    private String slotName;
    private String conditionDes;
    private String made;
    private String status;
    private LocalDateTime appointmentTime;
    private LocalDateTime createdAt;
    private CustomerResponse customer;
    private CarResponse car;
}
