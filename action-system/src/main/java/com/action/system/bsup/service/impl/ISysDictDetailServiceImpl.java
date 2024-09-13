package com.action.system.bsup.service.impl;

import com.action.common.enums.StatusType;
import com.action.system.bsup.struct.entity.SysDictDetail;
import com.action.system.bsup.mapper.SysDictDetailMapper;
import com.action.system.bsup.service.ISysDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 字典详情表
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/04/02
 */
@Service
@RequiredArgsConstructor
public class ISysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetail> implements ISysDictDetailService {
    private final SysDictDetailMapper sysDictDetailMapper;

    @Override
    public List<SysDictDetail> getDetailByCode(String code) {
        return list(this.getLambdaQueryWrapper()
                .eq(StringUtils.isNoneBlank(code), SysDictDetail::getCode, code)
                .eq(SysDictDetail::getStatus, StatusType.ENABLE.getStatus())
                .orderByAsc(SysDictDetail::getSort));
    }
}
