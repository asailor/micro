package com.gdsig.system.idempotent;

import com.gdsig.system.service.RedisService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author : xs
 * @date : 2023-08-17 10:53
 **/
public class RedisIdempotentStorage implements IdempotentStorage {

    @Resource
    RedisService redisService;

    @Override
    public void save(String idempotentId) {
        redisService.set(idempotentId, idempotentId, 10L, TimeUnit.MINUTES);
    }

    @Override
    public boolean delete(String idempotentId) {
        return redisService.remove(idempotentId);
    }
}
