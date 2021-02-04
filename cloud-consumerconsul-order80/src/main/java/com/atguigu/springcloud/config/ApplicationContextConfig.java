package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @program: cloud2020
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-04 10:41
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //注册中心的负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
