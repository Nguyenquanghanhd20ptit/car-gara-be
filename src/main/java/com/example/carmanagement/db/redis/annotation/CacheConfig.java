package com.example.carmanagement.db.redis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CacheConfig {
    String pattern();
    int expireSecond();
    boolean loadDataIfNotExited() default  true;
}
