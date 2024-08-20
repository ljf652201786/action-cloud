package com.action.system.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.struct.entity.SysScheduleTask;

import java.util.Set;


public interface ISysScheduleTaskService extends BaseMpService<SysScheduleTask> {
    Set<String> initScheduleTask();

    boolean isRunning(String sign);

    boolean isStopped(String sign);

    boolean addTask(SysScheduleTask sysScheduleTask);

    boolean updateTask(SysScheduleTask sysScheduleTask);

    boolean shutdownTask(String id);

    boolean shutdownAllTask();

    int getRuningTaskCount();

    void testTask();

    void testTask(String id);
}
