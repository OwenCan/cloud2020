package com.owen.ehredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 18:25
 **/
@EnableCaching //开启cache缓存
@SpringBootApplication
public class EhRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhRedisApplication.class);
    }
}
