package com.action.third.service.corerain.controller;

import com.action.common.core.common.Result;
import com.action.common.corerain.api.service._52.algorithm.AlgorithmApi_52;
import com.action.common.corerain.api.struct.vo.AlgorithmDegreeVo;
import com.action.common.corerain.api.struct.vo.ResultVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 鲲云平台算法管理控制类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/28
 */
@RequestMapping("corerain-algorithm")
@RestController
@RequiredArgsConstructor
public class AlgorithmController {
    private final AlgorithmApi_52 algorithmApi_52;

    /**
     * @Description: 获取算法告警等级
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/acm/v1/algorithm/degree
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "algorithmDegree", method = RequestMethod.GET)
    public Result<AlgorithmDegreeVo> algorithmDegree() {
        return Result.success(algorithmApi_52.algorithmDegree());
    }

    /**
     * @Description: 安装算法
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/am/v1/algorithm
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "installAlgorithm", method = RequestMethod.POST)
    public ResultVo installAlgorithm(@RequestParam("algorithm_uuid") String algorithm_uuid) {
        return algorithmApi_52.installAlgorithm(algorithm_uuid);
    }

    /**
     * @Description: 更新算法
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/am/v1/algorithm
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "updateAlgorithm", method = RequestMethod.PUT)
    public ResultVo updateAlgorithm(@RequestParam("algorithm_uuid") String algorithm_uuid) {
        return algorithmApi_52.updateAlgorithm(algorithm_uuid);
    }

    /**
     * @Description: 卸载算法
     * @return: Result
     * @link http://172.22.196.52:8081/openapi/am/v1/algorithm
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/8/20
     */
    @RequestMapping(value = "uninstallAlgorithm", method = RequestMethod.PUT)
    public ResultVo uninstallAlgorithm(@RequestParam("algorithm_uuid") String algorithm_uuid) {
        return algorithmApi_52.uninstallAlgorithm(algorithm_uuid);
    }
}
