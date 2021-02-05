package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: cloud2020
 * @description: 测试Hystrix
 * @author: Owen Zhao
 * @create: 2021-02-05 14:23
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    //正常情况
    @Override
    public String paymentInfo_OK(Long id) {
        return "线程池: " + Thread.currentThread().getName() + "Payment_OK" + id + "正常访问";
    }

    //超时情况
    @Override
    public String paymentInfo_Timeout(Long id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + "Payment_OK" + id + "正常访问";
    }
}
