package com.action.third.service.corerain.controller;

import com.action.common.core.common.Result;
import com.action.common.corerain.api.service._52.callback.CallBackApi_52;
import com.action.common.corerain.api.struct.dto.*;
import com.action.common.corerain.api.struct.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 鲲云平台回调控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@RequestMapping("corerain-callback")
@RestController
@RequiredArgsConstructor
public class CallBackController {
    private final CallBackApi_52 callBackApi_52;

    /**
     * @param page_size 每页数量
     * @param current   当前页
     * @Description: 告警推送配置-获取推送配置列表.
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/config
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "callBackPage", method = RequestMethod.GET)
    public CallBackConfigPageVo callBackPage(@RequestParam("page_size") Integer page_size, @RequestParam("current") Integer current) {
        return callBackApi_52.callBackPage(page_size, current);
    }

    /**
     * @Description: 告警推送配置-新增推送配置
     * 回调地址不能重复
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/config
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "saveConfig", method = RequestMethod.POST)
    public ResultVo saveConfig(@RequestBody CallBackConfigDto callBackConfigDto) {
        return callBackApi_52.saveConfig(callBackConfigDto);
    }

    /**
     * @Description: 告警推送配置-推送模拟测试
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/config/test
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "testConfig", method = RequestMethod.POST)
    public ResultVo testConfig(@RequestBody CallBackConfigTestDto callBackConfigTestDto) {
        return callBackApi_52.testConfig(callBackConfigTestDto);
    }

    /**
     * @Description: 告警推送配置-批量推送模拟测试
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/config/tests
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "batchTestConfig", method = RequestMethod.POST)
    public ResultVo batchTestConfig(@RequestBody CallBackUuidsDto callBackUuidsDto) {
        return callBackApi_52.batchTestConfig(callBackUuidsDto);
    }

    /**
     * @param callback_uuid callback配置的UUID  callback_uuid=d4a0c4a5bf9c426ea71167150da3d10c
     * @Description: 告警推送配置-获取模拟测试json数据.
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/config/test_json
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "getTestJson", method = RequestMethod.GET)
    Result<TestJsonVo> getTestJson(@RequestParam("callback_uuid") String callback_uuid) {
        return Result.success(callBackApi_52.getTestJson(callback_uuid));
    }

    /**
     * @param callback_uuid callback配置的UUID  callback_uuid=d4a0c4a5bf9c426ea71167150da3d10c
     * @Description: 告警推送配置-删除推送配置.
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/config/{callback_uuid}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "deleteConfig/{callback_uuid}", method = RequestMethod.DELETE)
    ResultVo deleteConfig(@PathVariable("callback_uuid") String callback_uuid) {
        return callBackApi_52.deleteConfig(callback_uuid);
    }

    /**
     * @param callback_uuid callback配置的UUID  callback_uuid=d4a0c4a5bf9c426ea71167150da3d10c
     * @Description: 告警推送配置-获取推送配置详情
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/config/{callback_uuid}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "getConfigInfo/{callback_uuid}", method = RequestMethod.GET)
    Result<CallBackConfigVo> getConfigInfo(@PathVariable("callback_uuid") String callback_uuid) {
        return Result.success(callBackApi_52.getConfigInfo(callback_uuid));
    }

    /**
     * @param callback_uuid callback配置的UUID  callback_uuid=d4a0c4a5bf9c426ea71167150da3d10c
     * @Description: 告警推送配置-更新推送配置
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/config/{callback_uuid}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "updateConfig/{callback_uuid}", method = RequestMethod.POST)
    ResultVo updateConfig(@PathVariable("callback_uuid") String callback_uuid, @RequestBody CallBackConfigDto callBackConfigDto) {
        return callBackApi_52.updateConfig(callback_uuid, callBackConfigDto);
    }

    /**
     * @param analysisJobDto interval、push_alarm_video_clip 属性非必填
     * @Description: 告警推送配置-删除关联任务推送
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/map_job
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "deleteAnalysisJob", method = RequestMethod.DELETE)
    ResultVo deleteAnalysisJob(@RequestBody AnalysisJobDto analysisJobDto) {
        return callBackApi_52.deleteAnalysisJob(analysisJobDto);
    }

    /**
     * @Description: 告警推送配置-获取关联任务推送
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/map_job
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "getAnalysisJob", method = RequestMethod.GET)
    Result<MapJobVo> getAnalysisJob() {
        return Result.success(callBackApi_52.getAnalysisJob());
    }

    /**
     * @param analysisJobDto interval、push_alarm_video_clip 属性非必填
     * @Description: 告警推送配置-新增关联任务推送.
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/map_job
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "saveAnalysisJob", method = RequestMethod.POST)
    ResultVo saveAnalysisJob(@RequestBody AnalysisJobDto analysisJobDto) {
        return callBackApi_52.saveAnalysisJob(analysisJobDto);
    }

    /**
     * @param analysisJobDto interval、push_alarm_video_clip 属性非必填
     * @Description: 告警推送配置-修改关联任务推送.
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/map_job
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "updateAnalysisJob", method = RequestMethod.PUT)
    ResultVo updateAnalysisJob(@RequestBody AnalysisJobDto analysisJobDto) {
        return callBackApi_52.updateAnalysisJob(analysisJobDto);
    }

    /**
     * @Description: 告警推送日志-获取推送日志列表.
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/cb/v1/callback/search
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "searchAnalysisJob", method = RequestMethod.POST)
    Result<CallBackPageVo> searchAnalysisJob(@RequestBody SearchAnalysisJobDto searchAnalysisJobDto) {
        return Result.success(callBackApi_52.searchAnalysisJob(searchAnalysisJobDto));
    }
}
