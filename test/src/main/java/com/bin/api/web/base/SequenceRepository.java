package com.bin.api.web.base;


import com.bin.webase.core.context.IRepository;
import com.bin.webase.core.context.ISequence;
import com.bin.webase.core.entity.DbDomain;
import com.google.common.collect.Lists;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SequenceRepository implements ISequence {

    private static final Long INIT_ID = 10000L;
    private static final String KEY = "seq_";
    private final StringRedisTemplate redisTemplate;

    public SequenceRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long newKey(Class clazz) {
        String key = clazz.getTypeName();
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().increment(key, INIT_ID);
            return INIT_ID;
        }
        return redisTemplate.opsForValue().increment(key, 1);
    }

    @Override
    public void init(IRepository repository) {
        String key = KEY + repository.getTableName();
        if (!redisTemplate.hasKey(key)) {
            Long maxId = repository.getMaxId();
            if (maxId != null) {
                redisTemplate.opsForValue().set(key, String.valueOf(maxId + 1));
            } else {
                redisTemplate.opsForValue().set(key, INIT_ID.toString());
            }
        }
    }

    @Override
    public Long newKey(DbDomain domain) {
        String key = KEY + domain.getTableName();
        return redisTemplate.opsForValue().increment(key);
    }


    @Override
    public List<Long> newKeys(DbDomain domain, Long size) {
        Long sequence = getSequence(domain, size);
        List<Long> result = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            result.add(sequence + i);
        }
        return result;
    }


    private Long getSequence(DbDomain domain, Long i) {
        String key = KEY + domain.getTableName();
        return redisTemplate.opsForValue().increment(key, i);
    }


}
