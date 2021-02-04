package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: cloud2020
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-04 10:32
 */
@SpringBootApplication
@EnableDiscoveryClient //consul服务发现注解
public class PaymentConsul8006 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentConsul8006.class, args);
    }
}
