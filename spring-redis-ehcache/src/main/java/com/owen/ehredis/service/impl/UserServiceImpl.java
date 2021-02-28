package com.owen.ehredis.service.impl;

import com.owen.ehredis.entity.User;
import com.owen.ehredis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 15:04
 **/
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Cacheable("userCache")
    @Override
    public User findById(long id) {
        User user = new User();
        user.setAge(11);
        user.setId(12);
        user.setPassWord("123");
        user.setSex(User.Sex.FM);
        user.setUserName("owen");
        //耗时操作
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findByPage(int startIndex, int limit) {
        return null;
    }

    @Cacheable("userCache")
    @Override
    public List<User> findBySex(User.Sex sex) {
        log.info(Thread.currentThread().getName() + "findBySex");
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("tony"+i);
            user.setPassWord("******");
            user.setSex(sex);
            user.setAge(32+i);
            users.add(user);
        }
        return users;
    }

    @Override
    public List<User> findByAge(int lessAge) {
        return null;
    }

    @Override
    public List<User> findByUsers(List<User> users) {
        return null;
    }

    @Cacheable("userCache")
    @Override
    public boolean update(User user) {
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }
}
