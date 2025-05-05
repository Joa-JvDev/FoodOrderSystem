package jva.dev.foodordersystem.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${cache.users.info.ttl}")
    private long cacheUsersInfoTtl;
    @Value("${cache.users.info.max-size}")
    private long cacheUsersInfoMaxSize;
    public static final String CACHE_USERS_INFO = "cache_users_info";

    @Bean
    public CacheManager cacheManager(){

        List<CaffeineCache> caches = new ArrayList<>();
        caches.add(buildCache(CACHE_USERS_INFO, cacheUsersInfoTtl, TimeUnit.HOURS, cacheUsersInfoMaxSize));

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);
        return cacheManager;
    };

    private static CaffeineCache buildCache(String name, long ttl, TimeUnit ttlUnit, long size) {
        return new CaffeineCache(name, Caffeine.newBuilder()
        .expireAfterWrite(ttl, ttlUnit)
                .maximumSize(size)
                .build());
    }

}
