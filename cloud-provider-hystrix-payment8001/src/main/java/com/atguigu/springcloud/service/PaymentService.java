package com.atguigu.springcloud.service;

public interface PaymentService {

    String paymentInfo_OK(Long id);

    String paymentInfo_Timeout(Long id);
}
