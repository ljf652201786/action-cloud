package com.action.business.service.Impl;

import com.action.business.mapper.TestCpMapper;
import com.action.business.service.ITestCpService;
import com.action.business.struct.entity.TestCp;
import com.action.business.struct.vo.TestCpVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: Test逻辑实现类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
@Service
@RequiredArgsConstructor
public class ITestCpServiceImpl extends ServiceImpl<TestCpMapper, TestCp> implements ITestCpService {
    private final TestCpMapper testCpMapper;

    @Override
    public List<TestCp> getTesCp(String id) {
        this.setConverter(TestCpVo.class);
        TestCpVo vo = toVo(this.getById("1"), TestCpVo.class);
        System.out.println(vo);
        this.selectListBy((e) -> e.getById("1"), TestCpVo.class);
        return testCpMapper.getTescp(id);
    }
}
