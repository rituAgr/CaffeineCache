package com.springbootModules.learning.CaffineCacheDemo.TopicFlow.cache;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.springbootModules.learning.CaffineCacheDemo.TopicFlow.TopicEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TopicCache {

    private Cache<Long, TopicEntity> cache;
    private CacheConfig defaultCacheConfig = new CacheConfig();

    public TopicCache(){
        cache = Caffeine.newBuilder()
                .expireAfterAccess(defaultCacheConfig.getExpireAfterWriteMilliSec(), TimeUnit.MILLISECONDS)
                .maximumSize(defaultCacheConfig.getCacheSize())
                .build();
    }

    public void addToCache(TopicEntity topicEntity){
        cache.put(topicEntity.getId(), topicEntity);
    }


    public void UpdateCache(TopicEntity topicEntity){
        cache.invalidate(topicEntity.getId());
        cache.put(topicEntity.getId(), topicEntity);
    }

    public TopicEntity getFromCache(Long id) {
        TopicEntity topicEntity = cache.getIfPresent(id);
        return topicEntity;
    }

    public void flushCache(){
        cache.cleanUp();
    }

    public void delFromCache(Long id){
        cache.invalidate(id);
    }

}
