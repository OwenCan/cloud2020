package com.owen.springtask.entity;

import java.io.Serializable;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 12:08
 **/

public class SysOperLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String operType;

    private String method;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
