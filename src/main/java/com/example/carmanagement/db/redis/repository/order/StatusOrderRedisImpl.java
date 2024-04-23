package com.example.carmanagement.db.redis.repository.order;

import com.example.carmanagement.commons.data.request.order.StatusOrderRequest;
import com.example.carmanagement.db.redis.annotation.CacheConfig;
import com.example.carmanagement.db.redis.repository.AbsRedisRepository;

@CacheConfig(pattern = "orderId:%s", expireSecond = 60)
public class StatusOrderRedisImpl extends AbsRedisRepository<String, StatusOrderRequest> implements IStatusOrderRedis {
    @Override
    public StatusOrderRequest getDataFromDb(String s) {
        return null;
    }
}
