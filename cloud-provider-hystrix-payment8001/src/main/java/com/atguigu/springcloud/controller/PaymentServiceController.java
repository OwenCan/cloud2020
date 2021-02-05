package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloud2020
 * @description: 测试Hystrix
 * @author: Owen Zhao
 * @create: 2021-02-05 14:26
 */
@RestController
@Slf4j
public class PaymentServiceController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/payment/ok/{id}")
    public String getPaymentOK(@PathVariable(value = "id") Long id) {
        return paymentService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/timeout/{id}")
    public String getPaymentTimeout(@PathVariable(value = "id") Long id) {
        return paymentService.paymentInfo_Timeout(id);
    }

}
