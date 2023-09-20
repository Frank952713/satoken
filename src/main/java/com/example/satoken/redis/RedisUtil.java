package com.example.satoken.redis;

import cn.hutool.extra.spring.SpringUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
public final class RedisUtil {

    private RedisUtil() {}

    /**
     * 获取固定前缀key
     */
    public static Set<String> getKeys(String keyPrefix) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        return stringRedisTemplate.keys(keyPrefix + "*");
    }

    /**
     * 获取key的过期时间
     */
    public static Long getTimeOut(String key) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 设置key的过期时间
     */
    public static void setTimeOut(String key, long time, TimeUnit timeUnit) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        stringRedisTemplate.expire(key,time, timeUnit);
    }

    /**
     * 获取字符串类型
     */
    public static String getString(String key) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        return opsForValue.get(key);
    }

    /**
     * 获取字符串类型
     */
    public static void setString(String key, String value, long time) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set(key, value);
        stringRedisTemplate.expire(key,time, TimeUnit.SECONDS);
    }

    /**
     * 获取字符串类型
     */
    public static void setString(String key, String value) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set(key, value);
    }

    /**
     * 尝试设置值
     */
    public static Boolean trySetString(String key, String value, long time) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        return opsForValue.setIfAbsent(key, value, time, TimeUnit.SECONDS);
    }


    public static void set(String key, Object value) {
        RedisTemplate bean = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        ValueOperations opsForValue = bean.opsForValue();
        opsForValue.set(key, value);
    }

    public static void set(String key, Object value, long time) {
        RedisTemplate bean = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        ValueOperations opsForValue = bean.opsForValue();
        opsForValue.set(key, value);
        bean.expire(key,time, TimeUnit.SECONDS);
    }

    public static Object get(String key) {
        RedisTemplate bean = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        ValueOperations opsForValue = bean.opsForValue();
        return opsForValue.get(key);
    }

    /**
     * 获取哈希类型
     */
    public static String getHashString(String key, String hashKey) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        HashOperations<String, Object, Object> forHash = stringRedisTemplate.opsForHash();
        return (String) forHash.get(key,hashKey);
    }

    /**
     * 获取哈希类型的全部key
     */
    public static Set<String> getHashKeys(String key) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        HashOperations<String, String, String> forHash = stringRedisTemplate.opsForHash();
        return forHash.keys(key);
    }

    /**
     * 设置哈希类型
     */
    public static void putHashAll(String key, Map<String, String> valueMap) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        HashOperations<String, Object, Object> forHash = stringRedisTemplate.opsForHash();
        forHash.putAll(key,valueMap);
    }

    /**
     * 设置哈希类型
     */
    public static void setHashString(String key, String hashKey, String value) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        HashOperations<String, Object, Object> forHash = stringRedisTemplate.opsForHash();
        forHash.put(key,hashKey,value);
    }

    /**
     * 查询是否含有该hash的key
     */
    public static Boolean hasHashKey(String key, String hashKey) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        HashOperations<String, Object, Object> forHash = stringRedisTemplate.opsForHash();
        return forHash.hasKey(key,hashKey);
    }

    /**
     * 删除数据
     */
    public static void delete(String key) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        stringRedisTemplate.delete(key);
    }

    /**
     * 删除哈希数据
     */
    public static void deleteHash(String key, String hashKey) {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        stringRedisTemplate.opsForHash().delete(key,hashKey);
    }

    /**
     * 获取锁
     */
    public static RLock getLock(String lockName) {
        RedissonClient redissonClient = SpringUtil.getBean(RedissonClient.class);
        return redissonClient.getLock(lockName);
    }

    /**
     * 加锁
     */
    public static void lock(String lockName) {
        RedissonClient redissonClient = SpringUtil.getBean(RedissonClient.class);
        RLock lock = redissonClient.getLock(lockName);
        lock.lock();
    }

    /**
     * 解锁
     */
    public static void unLock(String lockName) {
        RedissonClient redissonClient = SpringUtil.getBean(RedissonClient.class);
        RLock lock = redissonClient.getLock(lockName);
        lock.unlock();
    }

    /**
     * 获取分批导入时写入数据库的条数
     */
    public static Integer getSaveNumLimit() {
        String saveNumLimitStr = getString("ets:import:saveNumLimit");
        if (StringUtils.isEmpty(saveNumLimitStr)) {
            //默认2000条
            return 2000;
        }
        return Integer.valueOf(saveNumLimitStr);
    }

    /**
     * 获取excel导入时访问日志服务的间隔时间 单位 毫秒
     */
    public static Long getSaveLogStopTime() {
        String saveNumLimitStr = getString("ets:import:saveLogStopTime");
        if (StringUtils.isEmpty(saveNumLimitStr)) {
            //默认10毫秒
            return 10L;
        }
        return Long.valueOf(saveNumLimitStr);
    }

}
