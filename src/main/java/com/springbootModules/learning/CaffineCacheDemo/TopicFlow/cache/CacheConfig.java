package com.springbootModules.learning.CaffineCacheDemo.TopicFlow.cache;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor
@Getter
public class CacheConfig {

    public static final long CACHE_EXPIRE_MILLI_DEFAULT = Duration.ofSeconds(30).toMillis();
    public static final long CACHE_MAX_SIZE_DEFAULT = 100000000L; //10 M Entries

    private long expireAfterWriteMilliSec = CACHE_EXPIRE_MILLI_DEFAULT;
    private long cacheSize = CACHE_MAX_SIZE_DEFAULT;

}
