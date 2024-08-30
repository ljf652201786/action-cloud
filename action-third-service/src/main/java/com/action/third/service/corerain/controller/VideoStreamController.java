package com.action.third.service.corerain.controller;

import com.action.common.core.common.Result;
import com.action.common.corerain.api.service._52.video.stream.VideoStreamApi_52;
import com.action.common.corerain.api.struct.dto.*;
import com.action.common.corerain.api.struct.vo.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 鲲云平台视频流控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@RequestMapping("corerain-vs")
@RestController
@RequiredArgsConstructor
public class VideoStreamController {
    private final VideoStreamApi_52 videoStreamApi_52;

    /**
     * @param pageSize 每页数量
     * @param current  当前页
     * @param search   查询值 （ID/任务名称）
     * @Description: 获取任务列表
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/jobs
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobsPage", method = RequestMethod.GET)
    public VideoStreamPageVo jobsPage(@RequestParam("page_size") Integer pageSize, @RequestParam("current") Integer current, @RequestParam(value = "search", required = false) String search) {
        return videoStreamApi_52.jobsPage(pageSize, current, search);
    }

    /**
     * @param job_id 任务id
     * @Description: 获取任务详情
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/jobs/{job_id}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "getInfo/{job_id}", method = RequestMethod.GET)
    public Result<VideoStreamInfoVo> getInfo(@PathVariable("job_id") String job_id) {
        return Result.success(videoStreamApi_52.getInfo(job_id));
    }

    /**
     * @param videoStreamJobDto 任务对象
     * @Description: 新建任务
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/jobs
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result<JobVo> save(@RequestBody VideoStreamJobDto videoStreamJobDto) {
        return Result.success(videoStreamApi_52.save(videoStreamJobDto));
    }

    /**
     * @param jobDeleteDto 任务id集合对象
     * @Description: 批量删除任务
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/jobs
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "deleteBatch", method = RequestMethod.DELETE)
    public ResultVo deleteBatch(@RequestBody JobDeleteDto jobDeleteDto) {
        return videoStreamApi_52.deleteBatch(jobDeleteDto);
    }

    /**
     * @param job_id 任务id
     * @Description: 轮巡分析任务停用
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/turn/stop/{job_id}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "turnStop/{job_id}", method = RequestMethod.POST)
    public ResultVo turnStop(@PathVariable("job_id") String job_id) {
        return videoStreamApi_52.turnStop(job_id);
    }

    /**
     * @param turnRunDto 轮巡分析任务对象
     * @Description: 运行轮巡分析任务
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/turn/run
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "turnRun", method = RequestMethod.POST)
    public Result<CombinationListVO> turnRun(@RequestBody TurnRunDto turnRunDto) {
        return Result.success(videoStreamApi_52.turnRun(turnRunDto));
    }

    /**
     * @param job_id 任务id
     * @Description: 轮巡分析任务就绪检查
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/turn/check/{job_id}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "turnCheck/{job_id}", method = RequestMethod.GET)
    public Result turnCheck(@PathVariable("job_id") String job_id) {
        return Result.success(videoStreamApi_52.turnCheck(job_id));
    }

    /**
     * @param job_id 任务id
     * @Description: 通用视频分析任务停用
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/stop/{job_id}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobStop/{job_id}", method = RequestMethod.POST)
    public ResultVo jobStop(@PathVariable("job_id") String job_id) {
        return videoStreamApi_52.jobStop(job_id);
    }

    /**
     * @param turnRunDto 轮巡分析任务对象
     * @Description: 通用视频分析任务启用
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/run
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobRun", method = RequestMethod.POST)
    public Result<CombinationListVO> jobRun(@RequestBody TurnRunDto turnRunDto) {
        return Result.success(videoStreamApi_52.jobRun(turnRunDto));
    }

    /**
     * @param job_id 任务id
     * @Description: 导入视频流分析任务
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/import/{job_id}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobImport/{job_id}", method = RequestMethod.POST)
    public Result<CombinationListVO> jobImport(@PathVariable("job_id") String job_id, @RequestPart("cr_video_analyze_job") MultipartFile multipartFile) {
        return Result.success(videoStreamApi_52.jobImport(job_id, multipartFile));
    }

    /**
     * @param job_id 任务id
     * @Description: 导出视频流分析任务
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/export
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobExport", method = RequestMethod.GET)
    public Result jobExport(@RequestParam("job_id") String job_id, HttpServletResponse httpServletResponse) {
        return Result.success(videoStreamApi_52.jobExport(job_id, httpServletResponse));
    }

    /**
     * @param jobCombinationDto 任务摄像头算法组合对象
     * @Description: 选择摄像头和算法
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/combinations
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobCombinations", method = RequestMethod.POST)
    public Result<CombinationListVO> jobCombinations(@RequestBody JobCombinationDto jobCombinationDto) {
        return Result.success(videoStreamApi_52.jobCombinations(jobCombinationDto));
    }

    /**
     * @param job_id 任务id
     * @Description: 获取组合
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/combinations
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobCombinations", method = RequestMethod.GET)
    public Result<CombinationCfgListVo> jobCombinations(@RequestParam("job_id") String job_id) {
        return Result.success(videoStreamApi_52.jobCombinations(job_id));
    }

    /**
     * @param jobCombinationConfigDto 任务配置对象
     * @Description: 修改任务配置
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/combination/configs
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobCombinationConfigs", method = RequestMethod.POST)
    public Result jobCombinationConfigs(@RequestBody JobCombinationConfigDto jobCombinationConfigDto) {
        return Result.success(videoStreamApi_52.jobCombinationConfigs(jobCombinationConfigDto));
    }

    /**
     * @param current    每页数量
     * @param page_size  每页数量
     * @param sort_field 排序字段
     * @param sort_type  desc： 升序 | asc: 降序
     * @param job_id     任务id
     * @Description: 获取任务配置列表
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/combination/configs
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobCombinationConfigs", method = RequestMethod.GET)
    public JobCfgPageVo jobCombinationConfigs(@RequestParam("job_id") String job_id,
                                                      @RequestParam(value = "current", required = false) Integer current,
                                                      @RequestParam(value = "page_size", required = false) Integer page_size,
                                                      @RequestParam(value = "sort_field", required = false) String sort_field,
                                                      @RequestParam(value = "sort_type", required = false) String sort_type) {
        return videoStreamApi_52.jobCombinationConfigs(job_id, current, page_size, sort_field, sort_type);
    }

    /**
     * @param jobCombinationConfigDto 任务配置对象
     * @Description: 修改任务配置-CAISA4.0芯片使用
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/combination/caisa_v4/configs
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobCombinationCaisaV4Configs", method = RequestMethod.POST)
    public Result jobCombinationCaisaV4Configs(@RequestBody JobCombinationConfigDto jobCombinationConfigDto) {
        return Result.success(videoStreamApi_52.jobCombinationCaisaV4Configs(jobCombinationConfigDto));
    }

    /**
     * @param job_id 任务id
     * @Description: 通用视频分析任务就绪检查
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/vam/v1/stream/job/check/{job_id}
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "jobCheck/{job_id}", method = RequestMethod.GET)
    public Result jobCheck(@PathVariable("job_id") String job_id) {
        return Result.success(videoStreamApi_52.jobCheck(job_id));
    }
}
