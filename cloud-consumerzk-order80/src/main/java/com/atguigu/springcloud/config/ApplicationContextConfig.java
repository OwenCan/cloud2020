package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-03 23:07
 **/
@Configuration
public class ApplicationContextConfig {

    @Resource
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced  //负载均衡策略
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
