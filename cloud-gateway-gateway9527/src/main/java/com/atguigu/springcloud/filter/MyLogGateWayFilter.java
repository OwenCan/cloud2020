package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: cloud2020
 * @description: 全局过滤器
 * @author: Owen Zhao
 * @create: 2021-02-07 17:41
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    //过滤逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("---------------come in MyGateway");
        //请求投一定要带上uname
        String name = exchange.getRequest().getQueryParams().getFirst("uname");
        if (name == null) {
            log.info("--------------uname is null,reback");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        //链路链会进行下一个过滤请求
        return chain.filter(exchange);
    }

    //过滤器的优先级，数字越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
