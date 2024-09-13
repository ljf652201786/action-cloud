package com.action.system.sconf.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.sconf.service.ISysScheduleTaskService;
import com.action.system.sconf.struct.entity.SysScheduleTask;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Description: 定时任务管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("schedule")
@RequiredArgsConstructor
public class SysScheduleTaskController implements BaseController<ISysScheduleTaskService, SysScheduleTask> {
    private final ISysScheduleTaskService iSysScheduleTaskService;

    /**
     * @param query 查询对象
     * @Description: 定时任务列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysScheduleTask sysScheduleTask, BaseQuery query) {
        return this.page(iSysScheduleTaskService, sysScheduleTask, query);
    }

    /**
     * @param sysScheduleTask 定时任务对象
     * @Description: 保存定时任务详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysScheduleTask sysScheduleTask) {
        return Result.judge(iSysScheduleTaskService.addTask(sysScheduleTask));
    }

    /**
     * @param sysScheduleTask 定时任务对象
     * @Description: 更新定时任务详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysScheduleTask sysScheduleTask) {
        return Result.judge(iSysScheduleTaskService.updateTask(sysScheduleTask));
    }

    /**
     * @param id 定时任务id集合
     * @Description: 删除定时任务
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public Result deleteById(@RequestParam("id") String id) {
        return this.deleteById(iSysScheduleTaskService, id, _id -> Objects.nonNull(iSysScheduleTaskService.getById(_id)));
    }

    /**
     * @param id 定时任务id集合
     * @Description: 关闭定时任务
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "shutdownTask", method = RequestMethod.PUT)
    public Result shutdownTask(@RequestParam("id") String id) {
        return Result.judge(iSysScheduleTaskService.shutdownTask(id));
    }

    /**
     * @Description: 关闭所有定时任务
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "shutdownAllTask", method = RequestMethod.PUT)
    public Result shutdownAllTask() {
        return Result.judge(iSysScheduleTaskService.shutdownAllTask());
    }

    /**
     * @Description: 正在运行的定时任务数量
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "getRuningTaskCount", method = RequestMethod.PUT)
    public Result getRuningTaskCount() {
        return Result.success(iSysScheduleTaskService.getRuningTaskCount());
    }

    /**
     * @Description: 根据任务标识判断定时任务是否正在运行
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "isRunning", method = RequestMethod.GET)
    public Result isRunning(@RequestParam("sign") String sign) {
        return Result.judge(iSysScheduleTaskService.isRunning(sign));
    }

    /**
     * @Description: 根据任务标识判断定时任务是否停止
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/14
     */
    @RequestMapping(value = "isStopped", method = RequestMethod.GET)
    public Result isStopped(@RequestParam("sign") String sign) {
        return Result.judge(iSysScheduleTaskService.isStopped(sign));
    }
}
