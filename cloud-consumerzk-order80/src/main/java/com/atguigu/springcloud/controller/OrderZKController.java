package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-03 23:09
 **/
@RestController
@Slf4j
public class OrderZKController {

    private static final String SERVER_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/order80")
    public String getResult() {
        return restTemplate.getForObject(SERVER_URL + "/payment/zk", String.class);
    }
}
