package com.owen.ehredis;

import com.owen.ehredis.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 16:02
 **/
public class TestEhRedisCache {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService service = (UserService) context.getBean("userServiceImpl");
        System.out.println(service.findById(5l));
        System.out.println(service.findById(5l));
        System.out.println(service.findById(5l));
        System.out.println(service.findById(5l));
        System.out.println(service.findById(5l));
    }
}
