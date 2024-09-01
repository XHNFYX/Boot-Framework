package com.bfw.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.utils
 * @Author: 风花雪月
 * @CreateTime: 2024-09-02  10:17
 * @Description:Redis简单工具类
 * @Version: 1.0
 */
public class RedisUtil {
    //    判断TTL是否过期
    public static boolean getIsExpire(StringRedisTemplate stringRedisTemplate, String key) {
        Long expire = stringRedisTemplate.opsForValue().getOperations().getExpire(key);
        return expire > 5 || expire == -1;
    }
}
