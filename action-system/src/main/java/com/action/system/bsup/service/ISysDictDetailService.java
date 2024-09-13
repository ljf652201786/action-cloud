package com.action.system.bsup.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.bsup.struct.entity.SysDictDetail;

import java.util.List;

public interface ISysDictDetailService extends BaseMpService<SysDictDetail> {

    List<SysDictDetail> getDetailByCode(String code);
}
