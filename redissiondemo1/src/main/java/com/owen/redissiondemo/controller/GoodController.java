package com.owen.redissiondemo.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-21 17:29
 **/
@RestController
public class GoodController {

    //stringredistemplate是redistemplate的实现类
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private Redisson redisson;

    public static final String REDIS_LOCK = "owenlock";

    //目的是获取那个服务发生的
    @Value("${server.port}")
    private String port;

    @GetMapping("/bug_goods")
    public String bug_Goods() throws Exception {

        //分布式加锁过程
        //作为一个唯一的标识
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        //通过Redisson解决集群环境下的分布式锁
        RLock redissonLock = redisson.getLock(REDIS_LOCK);
        redissonLock.lock();

        try {
            //相当与string的setnx加分布式锁
            /*Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value);
            //避免服务器宕机，不能解锁
            stringRedisTemplate.expire(REDIS_LOCK, 10L, TimeUnit.SECONDS);*/
            Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);

            if (!aBoolean) {
                return "抢锁失败";
            }

            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNums = result == null ? 0 : Integer.parseInt(result);
            if (goodsNums > 0) {
                int realNumber = goodsNums - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                System.out.println("商品剩余：" + realNumber + "\t 商品购买成功，服务号为" + port);
                return "商品剩余：" + realNumber + "\t 商品购买成功，服务号为" + port;
            } else {
                System.out.println("商品剩余：" + goodsNums + "\t 商品购买失败，服务号为" + port);
            }
            return "商品剩余：" + goodsNums + "\t 商品购买失败，服务号为" + port;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            //判断当前线程和解锁的线程是同一个
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }

            //只能删除自己的锁
            /*if (stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)) {
                //解锁过程
                stringRedisTemplate.delete(REDIS_LOCK);
            }*/
            //通过redis事务来保证原子操作
            /*while (true) {
                //开启监控
                stringRedisTemplate.watch(REDIS_LOCK);
                if (stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)) {
                    //开始事务
                    stringRedisTemplate.setEnableTransactionSupport(true);
                    //事务开始的位置
                    stringRedisTemplate.multi();
                    //将删除操作添加到队列中
                    stringRedisTemplate.delete(REDIS_LOCK);
                    //执行删除队列操作，成功则返回list
                    List<Object> list = stringRedisTemplate.exec();
                    //如果当前list为空，说明没有删除成功，有别人在修改我们的操作，继续循环执行
                    if (list == null) {
                        continue;
                    }
                }
                //停止监控
                stringRedisTemplate.unwatch();
                break;
            }*/
            //申请jedis资源
            /*Jedis jedis = RedisUtils.getJedis();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] \n" +
                    "    then \n" +
                    "        return redis.call('del', KEYS[1]) \n" +
                    "    else \n" +
                    "        return 0 \n" +
                    "end";
            try {
                Object eval = jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
                if ("1".equals(eval.toString())) {
                    System.out.println("delete redis success");
                } else {
                    System.out.println("delete redis error");
                }
            }finally {
                //关闭jedis
                if (null != jedis) {
                    jedis.close();
                }
            }*/


        }
        return null;
    }
}
