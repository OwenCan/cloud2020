package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: cloud2020
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-05 10:00
 */
@SpringBootApplication
@EnableFeignClients
public class ConsumerFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignMain80.class, args);
    }
}
