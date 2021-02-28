package com.owen.springtask;

import com.owen.springtask.task.BusinessType;
import com.owen.springtask.task.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SpringtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringtaskApplication.class, args);
        System.out.println("执行之前");
    }


}
