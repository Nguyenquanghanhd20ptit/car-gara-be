package com.example.carmanagement.commons.data.response.statistic.nevenue;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class StatisticRevenueDataResponse {
    public List<StatisticRevenueResponse> items;
    public Long totalOrder;
    public Long totalCustomer;
    public Double totalRevenue;
}
