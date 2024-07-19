package com.action.system.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.system.service.ISysLimitObjService;
import com.action.system.service.ISysRuleService;
import com.action.system.struct.entity.SysLimitObj;
import com.action.system.struct.entity.SysRule;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 限制对象管理
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@RestController
@RequestMapping("limitObj")
@RequiredArgsConstructor
public class SysLimitObjController implements BaseController<ISysLimitObjService, SysLimitObj> {
    private final ISysLimitObjService iSysLimitObjService;
    private final ISysRuleService iSysRuleService;

    /**
     * @param query 查询 查询参数
     * @Description: 限制对象列表
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysLimitObj sysLimitObj, BaseQuery query) {
        return this.page(iSysLimitObjService, sysLimitObj, query);
    }

    /**
     * @param id 限制对象id
     * @Description: 获取限制对象详情
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Result getInfo(String id) {
        return this.getInfoById(iSysLimitObjService, id);
    }

    /**
     * @param sysLimitObj 限制对象
     * @Description: 保存限制对象
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody SysLimitObj sysLimitObj) {
        SysLimitObj slo = iSysLimitObjService.getOne(this.getLambdaQueryWrapper().eq(SysLimitObj::getLimitObj, sysLimitObj.getLimitObj()));
        if (Objects.nonNull(slo)) {
            return Result.failed("权限标识已存在");
        }
        boolean isSave = iSysLimitObjService.save(sysLimitObj);
        if (isSave) {
            return Result.success("保存数据成功");
        }
        return Result.failed("数据保存失败");
    }

    /**
     * @param sysLimitObj 限制对象
     * @Description: 更新限制对象
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Result update(@RequestBody SysLimitObj sysLimitObj) {
        SysLimitObj oldLimitObj = iSysLimitObjService.getById(sysLimitObj.getId());
        SysLimitObj slo = iSysLimitObjService.getOne(this.getLambdaQueryWrapper().eq(SysLimitObj::getLimitObj, sysLimitObj.getLimitObj()));
        if (!oldLimitObj.getLimitObj().equals(sysLimitObj.getLimitObj()) && Objects.nonNull(slo)) {
            return Result.failed("权限标识已使用");
        }
        boolean isUpdate = iSysLimitObjService.updateById(sysLimitObj);
        if (isUpdate) {
            return Result.success("更新数据成功");
        }
        return Result.failed("更新数据失败");
    }

    /**
     * @param ids 限制对象id集合
     * @Description: 批量删除限制对象数据
     * @return: Result 结果集
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/4/3
     */
    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        List<String> idList = new ArrayList<>();
        List<String> idExistList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            long num = iSysRuleService.count(this.getLambdaQueryWrapper(new SysRule()).eq(SysRule::getLimitObjId, ids.get(i)));
            if (num == 0) {
                idList.add(ids.get(i));
            } else {
                idExistList.add(ids.get(i));
            }
        }
        if (idList.size() > 0) {
            iSysLimitObjService.removeBatchByIds(idList);
        }
        if (idExistList.size() > 0) {
            return Result.failed("删除失败，因为包含正被用户使用", idExistList);
        }
        return Result.success("批量通过id删除数据成功");
    }

}
