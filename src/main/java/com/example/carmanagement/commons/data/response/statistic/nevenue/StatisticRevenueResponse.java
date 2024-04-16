package com.example.carmanagement.commons.data.response.statistic.nevenue;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatisticRevenueResponse {
    private Integer customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private Integer numCorrection;
    private Double totalPrice;
    private String currency;
}
