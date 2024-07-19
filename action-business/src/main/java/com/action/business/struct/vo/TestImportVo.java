package com.action.business.struct.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: test导入vo
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/03
 */
@Data
public class TestImportVo {

    @ExcelProperty(value = "id")
    private String id;
    @ExcelProperty(value = "姓名")
    private String name;
}
