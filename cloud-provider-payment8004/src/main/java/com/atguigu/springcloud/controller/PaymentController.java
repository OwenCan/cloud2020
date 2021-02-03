package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @program: cloud2020
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-03 16:48
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/")
    public String getPayment() {
        return "springcloud zookeeper " + serverPort + UUID.randomUUID().toString();
    }
}
