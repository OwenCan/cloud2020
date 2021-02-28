package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.sql.Time;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-27 08:46
 **/
@Component
@Slf4j
public class MyLogTimeLogFilter implements GlobalFilter, Ordered {

    private final static String Time_Begin = "elapsedTimeBegin";

    /**
     * 自定义过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("开始服务时间记录");
        exchange.getAttributes().put(Time_Begin, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(()->{
                    Long startTime = exchange.getAttribute(Time_Begin);
                    if (startTime != null) {
                        System.out.println(exchange.getRequest().getURI().getRawPath() + ":" + (System.currentTimeMillis() - startTime) + "ms");
                        log.info("开始服务时间记录"+exchange.getRequest().getURI().getRawPath() + ":" + (System.currentTimeMillis() - startTime) + "ms");
                    }
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
