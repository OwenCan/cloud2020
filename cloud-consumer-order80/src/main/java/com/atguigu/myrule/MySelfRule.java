package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud2020
 * @description: 自定义的Ribbon负载均衡算法不能再@ComponentScan包及子包下
 *               意味着说不能再@SpringBootApplication包下
 * @author: Owen Zhao
 * @create: 2021-02-04 15:05
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        //定义为随机策略
        return new RandomRule();
    }
}
