package com.owen.ehredis.cacheutils;


import com.owen.ehredis.service.impl.UserServiceImpl;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;
import java.util.concurrent.Callable;

/**
 * @description: spring集成ehcache+redis
 * @author: Owen Zhao
 * @create: 2021-02-28 14:55
 **/
public class EhRedisCache implements Cache {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private String name;

    private net.sf.ehcache.Cache ehCache;

    private RedisTemplate<String, Object> redisTemplate;

    private long liveTime = 1 * 60 * 60;//默认1小时


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    /**
     * 从redis中获取数据，并拿到本地
     * @param key
     * @return
     * @throws DataAccessException
     */
    @Override
    public ValueWrapper get(Object key)  throws DataAccessException {
        Element value = ehCache.get(key);
        log.info("cache l1(ehcache):{}={}", key, value);
        if (value != null) {
            return (value != null ? new SimpleValueWrapper(value.getObjectValue()) : null);
        }
        final String keyStr = key.toString();
//        Object objectValue = redisTemplate.execute(new RedisCallback<Object>() {
        Object objectValue = redisTemplate.execute(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection connection) {
                byte[] key = keyStr.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                //如果缓存大于0，重制缓存为过期时间
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                //将byte数组转化为对象
                return toObject(value);
            }
        }, true);
        //从redis中取出来，放进去本地缓存
        ehCache.put(new Element(key, objectValue));
        log.info("cache 2(redis):{}={}", key, objectValue);
        return objectValue != null ? new SimpleValueWrapper(objectValue) : null;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public net.sf.ehcache.Cache getEhCache() {
        return ehCache;
    }

    public void setEhCache(net.sf.ehcache.Cache ehCache) {
        this.ehCache = ehCache;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(long liveTime) {
        this.liveTime = liveTime;
    }

    @Override
    public void put(Object key, Object value) {
        ehCache.put(new Element(key, value));
        final String keyStr = key.toString();
        final Object valueStr = value;
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyb = keyStr.getBytes();
                //将对象转化为byte数组
                byte[] valueb = toByteArray(valueStr);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        }, true);
    }


    /**
     * 提出key
     * @param key
     */
    @Override
    public void evict(Object key) {
        ehCache.remove(key);
        final String keyStr = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyStr.getBytes());
            }
        }, true);
    }

    /**
     * 清除全部key
     */
    @Override
    public void clear() {
        ehCache.removeAll();
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "clear all done";
            }
        }, true);
    }


    /**
     * 将对象转化为byte数组
     *
     * @param valueStr
     * @return
     */
    private byte[] toByteArray(Object valueStr) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(valueStr);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }


    /**
     * byte[]转化成Object
     * @param value
     * @return
     */
    private Object toObject(byte[] value) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(value);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
