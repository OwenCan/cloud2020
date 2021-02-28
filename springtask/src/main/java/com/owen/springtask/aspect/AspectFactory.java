package com.owen.springtask.aspect;

import com.owen.springtask.entity.SysOperLog;
import com.owen.springtask.service.ISysOperLogService;
import org.springframework.beans.factory.BeanFactory;

import java.util.TimerTask;

/**
 * @description:
 * @author: Owen Zhao
 * @create: 2021-02-28 12:24
 **/
public class AspectFactory {

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setStatus(1);
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            }
        };
    }
}
