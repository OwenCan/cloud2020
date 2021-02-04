package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-01 22:38
 **/
@RestController
@Slf4j/*用lombok的记录日志注解*/
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    //获取application.yml中的属性
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "插入成功，serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "插入成功，serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败" + id, null);
        }
    }

    @GetMapping(value = "/oayment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();

        //获取eureaka上面的application的名字，即服务名称
        for (String service : services) {
            log.info("****service***" + service);
        }

        //获取服务名称下的具体信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "--" + instance.getHost() + "--" + instance.getPort() + "--" + instance.getUri());
        }

        return this.discoveryClient;
    }

    /**
     * 测试自定义负载均衡器
     *
     * @return
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
