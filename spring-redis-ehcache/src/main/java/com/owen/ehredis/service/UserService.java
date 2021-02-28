package com.owen.ehredis.service;

import com.owen.ehredis.entity.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    List<User> findByPage(int startIndex, int limit);

    List<User> findBySex(User.Sex sex);

    List<User> findByAge(int lessAge);

    List<User> findByUsers(List<User> users);

    boolean update(User user);

    boolean deleteById(long id);
}
