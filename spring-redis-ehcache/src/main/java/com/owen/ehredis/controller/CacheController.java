package com.owen.ehredis.controller;

import com.owen.ehredis.cacheutils.CacheManagerConfig;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 18:27
 **/
@Controller
@CacheConfig(cacheNames = {"userCache"})//配置缓存基本信息
public class CacheController {


    //方法查询数据后存入缓存
    @Cacheable(key = "#key", cacheManager = CacheManagerConfig.CacheManagerNames.EHCACHE_CACHE_MAANGER, value = "CACHE_EHCACHE")
    @RequestMapping("/getEhcache")
    @ResponseBody
    public String getEhcache(String key) {
        System.out.println("——  ehcache  ——");
        return "cache:" + key;
    }

    @Cacheable(key = "#key",  value = "CACHE_REDIS")
    @RequestMapping("/getRedis")
    @ResponseBody
    public String getRedis(String key) {
        System.out.println("——  redis Cache  ——");
        return "cache:" + key;
    }
}
