package com.fx.demo.order.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);

    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
