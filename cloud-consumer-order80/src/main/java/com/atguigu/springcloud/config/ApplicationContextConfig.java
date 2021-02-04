package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: cloud2020
 * @description: RestTemplate配置
 * @author: Owen Zhao
 * @create: 2021-02-02 11:47
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced //RestTemplate提供的负载均衡策略,否则会报错，注释是因为手动测试调用自己写的负载均衡器
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
