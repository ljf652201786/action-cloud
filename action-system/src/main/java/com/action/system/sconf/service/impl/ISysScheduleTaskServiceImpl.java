package com.action.system.sconf.service.impl;

import com.action.common.core.scheduled.ISchedule;
import com.action.common.enums.StatusType;
import com.action.system.sconf.mapper.SysScheduleTaskMapper;
import com.action.system.sconf.service.ISysScheduleTaskService;
import com.action.system.sconf.struct.converter.ScheduleTaskConverter;
import com.action.system.sconf.struct.dto.SysScheduleTaskDto;
import com.action.system.sconf.struct.entity.SysScheduleTask;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: 定时任务表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysScheduleTaskServiceImpl extends ServiceImpl<SysScheduleTaskMapper, SysScheduleTask> implements ISysScheduleTaskService {
    private final ISchedule<SysScheduleTaskDto> iSchedule;
    private final ScheduleTaskConverter scheduleTaskConverter;

    @Override
    public Set<String> initScheduleTask() {
        SysScheduleTask sysScheduleTask = new SysScheduleTask();
        sysScheduleTask.setStatus(StatusType.ENABLE.getStatus());
        List<SysScheduleTaskDto> taskList = getSelectList(sysScheduleTask, ScheduleTaskConverter.INSTANCE::sysScheduleTaskrToDto);
        return iSchedule.initLoadTask(taskList);
    }

    @Override
    public boolean isRunning(String sign) {
        return iSchedule.isRunning(sign);
    }

    @Override
    public boolean isStopped(String sign) {
        return iSchedule.isStopped(sign);
    }

    @Override
    public boolean addTask(SysScheduleTask sysScheduleTask) {
        if (Objects.isNull(Objects.requireNonNull(sysScheduleTask, "Task cannot be empty").getSign())) {
            return false;
        }
        //持久化数据库
        sysScheduleTask.setStatus(StatusType.ENABLE.getStatus());
        boolean isSave = save(sysScheduleTask);
        if (isSave) {
            SysScheduleTaskDto sysScheduleTaskDto = scheduleTaskConverter.sysScheduleTaskrToDto(sysScheduleTask);
            return Objects.nonNull(iSchedule.addTask(sysScheduleTaskDto));
        }
        return false;
    }

    @Override
    public boolean updateTask(SysScheduleTask sysScheduleTask) {
        String id = Objects.requireNonNull(sysScheduleTask, "Task cannot be empty").getId();
        AtomicBoolean _isUpdate = new AtomicBoolean(false);
        getOptById(id).ifPresent(task -> {
            //更新定时任务时，不能处于运行状态
            if (!isRunning(task.getSign())) {
                sysScheduleTask.setStatus(StatusType.ENABLE.getStatus());
                boolean isUpdate = updateById(sysScheduleTask);
                if (isUpdate) {
                    SysScheduleTaskDto sysScheduleTaskDto = scheduleTaskConverter.sysScheduleTaskrToDto(sysScheduleTask);
                    _isUpdate.set(iSchedule.updateTask(sysScheduleTaskDto));
                }
            }
        });
        return _isUpdate.get();
    }

    @Override
    public boolean shutdownTask(String id) {
        AtomicBoolean _isShutdown = new AtomicBoolean(false);
        getOptById(id).ifPresent(sysScheduleTask -> {
            if (StringUtils.isNotEmpty(sysScheduleTask.getSign())) {
                _isShutdown.set(iSchedule.shutdownTask(sysScheduleTask.getSign()));
                sysScheduleTask.setStatus(StatusType.DISABLED.getStatus());
                updateById(sysScheduleTask);
            }
        });
        return _isShutdown.get();
    }

    @Override
    public boolean shutdownAllTask() {
        iSchedule.shutdownAllTask();
        //修改状态
        return update(this.getLambdaUpdateWrapper().set(SysScheduleTask::getStatus, StatusType.DISABLED.getStatus()));
    }

    @Override
    public int getRuningTaskCount() {
        return iSchedule.getRuningTaskCount();
    }

    @Override
    public void testTask() {
        System.out.println("无参方法定时任务");
    }

    @Override
    public void testTask(String id) {
        System.out.println("有参方法定时任务，参数为：" + id);
    }

}
