package com.owen.springtask;

import com.owen.springtask.task.BusinessType;
import com.owen.springtask.task.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 12:39
 **/
@SpringBootTest
public class test {

    @Test
    @Log(title = "出口运输工具申报查询", businessType = BusinessType.UPDATE)
    public void editSave() {
        System.out.println("执行出口运输工具申报查询");
    }
}
