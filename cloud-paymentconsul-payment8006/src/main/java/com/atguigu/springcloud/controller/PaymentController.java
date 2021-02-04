package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program: cloud2020
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-04 10:33
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/consul")
    public String getServerPort() {
        return "springcloud with consul:" + serverPort + "\t"
                + UUID.randomUUID().toString();
    }
}
