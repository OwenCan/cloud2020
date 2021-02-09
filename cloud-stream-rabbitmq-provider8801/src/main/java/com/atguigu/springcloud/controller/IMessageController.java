package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-09 11:50
 **/
@RestController
public class IMessageController {
    @Resource
    private IMessageProvider provider;

    @GetMapping("/sendMessage")
    public String send(){
        return provider.send();
    }
}

