package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2020
 * @description: Feign的日志级别
 * @author: Owen Zhao
 * @create: 2021-02-05 10:22
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level getFeignLevel() {
        return Logger.Level.FULL;
    }
}
