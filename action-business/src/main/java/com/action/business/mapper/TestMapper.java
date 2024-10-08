package com.action.business.mapper;

import com.action.business.struct.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/31
 */
public interface TestMapper extends BaseMapper<Test> {

    List<Test> getTes(@Param("id") String id);
}
