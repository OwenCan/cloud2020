package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: cloud2020
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-02 11:20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    /*因为数据库是id是bitint类型，所以这里用Long*/
    private Long id;
    private String serial;
}