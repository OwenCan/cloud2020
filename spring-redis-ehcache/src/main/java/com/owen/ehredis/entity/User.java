package com.owen.ehredis.entity;

import java.io.Serializable;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 15:53
 **/
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Sex{
        M,FM
    }
    private long id;
    private String userName;
    private String passWord;
    private int age;
    private Sex sex;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Sex getSex() {
        return sex;
    }
    public void setSex(Sex sex) {
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", passWord="
                + passWord + ", age=" + age + ", sex=" + sex + "]";
    }

}