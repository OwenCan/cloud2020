package com.owen.springtask.entity;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 12:05
 **/
public class SysUser {

    private String username;

    private String passwoed;

    public SysUser(String username, String passwoed) {
        this.username = username;
        this.passwoed = passwoed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswoed() {
        return passwoed;
    }

    public void setPasswoed(String passwoed) {
        this.passwoed = passwoed;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "username='" + username + '\'' +
                ", passwoed='" + passwoed + '\'' +
                '}';
    }
}
