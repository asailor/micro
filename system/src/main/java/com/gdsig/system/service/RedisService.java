package com.gdsig.system.service;

import com.gdsig.system.helper.RedisHelper;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 服务类
 *
 * @author huihu
 * @date 2021-08-31
 */
@Service
public class RedisService {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    RedisHelper redisHelper;

    /**
     * 写入缓存
     *
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     *
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除对应的value
     *
     * @param keys 键集合
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern 模式
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key 键
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key 键
     * @return boolean
     */
    public Boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key 键
     * @return Object
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 读取缓存
     *
     * @param key 键
     * @return Object
     */
    public Object getNoException(final String key) {
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            return operations.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 哈希 添加
     *
     * @param key     键
     * @param hashKey Hash键
     * @param value   值
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希获取数据
     *
     * @param key     键
     * @param hashKey Hash键
     * @return Object
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 哈希删除数据
     *
     * @param key     键
     * @param hashKey Hash键
     * @return Object
     */
    public Object delete(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.delete(key, hashKey);
    }

    /**
     * 列表添加
     *
     * @param key   键
     * @param value 值
     */
    public void listPush(String key, Object value) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(key, value);
    }

    /**
     * 列表获取
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @return List
     */
    public List<Object> listRange(String key, long start, long end) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(key, start, end);
    }

    /**
     * 集合删除
     *
     * @param key   键
     * @param value 值
     */
    public void remove(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.remove(key, value);
    }

    /**
     * 集合添加
     *
     * @param key   键
     * @param value 值
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 仅当hashKey不存在时才设置
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public void put(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 获取所有哈希表中的字段
     *
     * @param key
     * @return
     */
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 集合添加
     *
     * @param key   键
     * @param value 值
     */
    public Boolean isMember(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.isMember(key, value);
    }

    /**
     * 集合获取
     *
     * @param key 键
     * @return Set
     */
    public Set<Object> members(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     *
     * @param key   键
     * @param value 值
     * @param score 分数
     */
    public void zAdd(String key, Object value, double score) {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        zSet.add(key, value, score);
    }

    /**
     * 有序集合获取
     *
     * @param key    键
     * @param score1 分数1
     * @param score2 分数2
     * @return Set
     */
    public Set<Object> rangeByScore(String key, double score1, double score2) {
        ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
        return zSet.rangeByScore(key, score1, score2);
    }

    public boolean notRunning() {
        try {
            exists("");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public Object getValueForMap(String key, String hashKey) {
        try {
            return redisHelper.getValueForMap(key, hashKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getString(String key, String hashKey) {
        try {
            Object obj = redisHelper.getValueForMap(key, hashKey);
            return obj == null ? null : obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void saveForMap(String key, String hashKey, Object obj) {
        redisHelper.saveForMap(key, hashKey, obj);
    }

    public boolean saveForExpire(String key, String hashKey, Object obj, int timeoutSec) {
        try {
            redisHelper.save(key, hashKey, obj);
            redisHelper.expire(key, timeoutSec);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
