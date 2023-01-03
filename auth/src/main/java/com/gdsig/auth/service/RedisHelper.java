package com.gdsig.auth.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Service
public class RedisHelper {

    @Resource
    public RedisTemplate<String, Object> redisTemplate;

    public void save(String key, Object value, int timeoutSec) {

        ValueOperations<String, Object> valOps = redisTemplate.opsForValue();
        valOps.set(key, value, timeoutSec, TimeUnit.SECONDS);
    }

    public void save(String key, String hashKey, Object value) {
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        hashOps.put(key, hashKey, (value == null ? "" : value.toString()));
    }

    public void saveForMap(String key, String hashKey, Object value) {

        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        hashOps.put(key, hashKey, value);
    }

    public void saveMap(String key, Map<String, Object> map) {

        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        hashOps.putAll(key, map);
    }

    public void saveList(String key, List<?> list) {

        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        redisTemplate.delete(key);
        listOps.rightPushAll(key, list.toArray());
    }

    public void delete(String key) {

        redisTemplate.delete(key);
    }

    public Object getValue(String key) {
        ValueOperations<String, Object> valOps = redisTemplate.opsForValue();
        return valOps.get(key);
    }

    public Object getValueForMap(String key, String hashKey) {

        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        return hashOps.get(key, hashKey);
    }
    
    public Map<String, Object> getMap(String key) throws Exception {

        Map<String, Object> map = new HashMap<>();
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        Set<String> set = hashOps.keys(key);
        if (null != set) {
            for (String hashKey : set) {
                Object val = getValueForMap(key, hashKey);
                map.put(hashKey, val);
            }
        }

        return map;
    }

    public List<Object> getValueForList(String key) {

        ListOperations<String, Object> listOps = redisTemplate.opsForList();
        List<Object> list = listOps.range(key, 0, listOps.size(key));
        if (null != list && !list.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<Object> listValue = (List<Object>) list.get(0);
            return listValue;
        }
        return null;
    }

    public void expire(String key, int timeoutSec) {

        redisTemplate.expire(key, timeoutSec, TimeUnit.SECONDS);
    }

    public void expire(String key, long timeoutSec, TimeUnit unit) {

        redisTemplate.expire(key, timeoutSec, unit);
    }

    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }


}
