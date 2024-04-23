package com.example.carmanagement.db.redis.repository.order;

import com.example.carmanagement.commons.data.request.order.StatusOrderRequest;
import com.example.carmanagement.db.redis.repository.IBaseRedisRepository;

public interface IStatusOrderRedis extends IBaseRedisRepository<String,StatusOrderRequest> {
}
