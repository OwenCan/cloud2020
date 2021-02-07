package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @description: 实现Feign接口，统一Hystrix方法处理
 * @author: Owen Zhao
 * @create: 2021-02-06 16:47
 **/
@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_OK,o(╥ ﹏ ╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "----PaymentFallbackService fall back paymentInfo_Timeout,o(╥ ﹏ ╥)o";
    }
}
