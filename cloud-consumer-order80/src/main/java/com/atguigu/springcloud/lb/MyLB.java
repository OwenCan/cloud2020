package com.atguigu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: cloud2020
 * @description: 自定义负载均衡器
 * @author: Owen Zhao
 * @create: 2021-02-04 16:24
 */
@Component
@Slf4j
public class MyLB implements LoadBalancer {

    //定义初始类
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    //模拟CAS方法
    private int getAndIncrement() {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("*****next ：" + next);
        return next;
    }

    //请求的第几次数(Q) % 服务器的数量(F) = 实际调用服务器下标
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int nextIndex = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(nextIndex);
    }

}
