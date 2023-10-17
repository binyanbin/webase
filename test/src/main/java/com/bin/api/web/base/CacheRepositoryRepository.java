package com.bin.api.web.base;


import com.bin.webase.core.context.ICacheRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class CacheRepositoryRepository implements ICacheRepository {
    private final StringRedisTemplate redisTemplate;

    public CacheRepositoryRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    @Override
    public void set(String key, String value, long expires) {
        redisTemplate.opsForValue().set(key, value, expires, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, String value) {
        if (StringUtils.hasText(key)) {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String key) {
        Boolean result = redisTemplate.hasKey(key);
        if (result == null) {
            return false;
        } else {
            return result;
        }
    }

    @Override
    public long increment(String key, long expires) {
        long result = redisTemplate.opsForValue().increment(key);
        long ttl = redisTemplate.getExpire(key);
        if (ttl == -1L) {
            redisTemplate.expire(key, expires, TimeUnit.SECONDS);
        }
        return result;
    }
}