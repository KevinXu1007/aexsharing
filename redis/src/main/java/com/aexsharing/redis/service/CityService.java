package com.aexsharing.redis.service;

import com.aexsharing.redis.model.CityInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author : Kevin Xu
 * Date: 2018/6/26
 */

@Component
@CacheConfig(cacheNames = "CityService")
public class CityService {

    @Cacheable
    public CityInfo getCity(int id, String city) {
        return new CityInfo(id, city);
    }
}
