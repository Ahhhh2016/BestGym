package com.example.demo.auth.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存储String 类型
     */
    public void set(String key, String value) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);
    }

    /**
     * 存储String 类型，并设置过期时间
     */
    public void setAndExpire(String key, String value,long expire) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value,expire,TimeUnit.SECONDS);
    }

    /**
     * redis 存储list类型 并设置过期时间
     * @param key
     * @param list
     */
    public void setListAndExpire(String key,List list){
        redisTemplate.opsForList().rightPushAll(key,list);
    }

    /**
     * 获取list
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getList(String key){
        return (T) redisTemplate.opsForList().range(key,0,-1);
    }

    /**
     * redis 存储Map类型 并设置过期时间
     * @param key
     * @param map
     * @param expire
     */
    public void setHashAndExpire(String key, Map map, long expire){
        redisTemplate.opsForHash().putAll(key,map);
        this.expire(key,expire);
    }

    /**
     * 获取hash-value
     * @param key
     * @return
     */
    public Object getHashValue(String key, String hashKey){
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取key对应的hash表的所有value
     * @param key
     * @return
     */
    public List<Object> getHashValue(String key){
        return redisTemplate.opsForHash().values(key);
    }




    /**
     * 获取数据
     */
    public String get(String key) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 设置超期时间
     */
    public boolean expire(String key, long expire) {
        Boolean expire2 = stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return expire2;
    }

    /**
     * 存储数据 并设置超时时间
     */
    public <T> void setObjectAndExpire(String key, T value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 删除数据
     */
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }


    /**
     * 自增操作
     *
     * @param delta 自增步长
     */
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 查询key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }


}
