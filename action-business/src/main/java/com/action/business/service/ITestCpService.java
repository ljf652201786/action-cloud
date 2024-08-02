package com.action.business.service;

import com.action.business.struct.entity.TestCp;
import com.action.common.mybatisplus.extend.base.BaseMpService;

import java.util.List;

public interface ITestCpService extends BaseMpService<TestCp> {
    List<TestCp> getTesCp(String id);
}
