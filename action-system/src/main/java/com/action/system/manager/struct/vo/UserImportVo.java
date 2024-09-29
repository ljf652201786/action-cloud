package com.action.system.manager.struct.vo;

import com.action.common.core.enums.DelType;
import com.action.common.core.enums.StatusType;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 用户导入vo
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/03
 */
@Data
public class UserImportVo {
    /*
     * 默认设置
     * */
    private String isdelete = DelType.EXIST.getStatus();
    private String status = StatusType.ENABLE.getStatus();
    /*
     * excel必填
     * */
    @ExcelProperty(value = "用户账号")
    private String userName;
    @ExcelProperty(value = "用户邮箱")
    private String email;
    @ExcelProperty(value = "手机号码")
    private String phone;
    /*
     * excel选填
     * */
    @ExcelProperty(value = "用户昵称")
    private String nickName;
    @ExcelProperty(value = "用户性别")
    private String sex;
    //... 额外属性，根据实际的需求而定

    /*
     * 检查导入必填项
     * */
    public boolean checkRequired() {
        return StringUtils.isNoneBlank(this.getUserName()) &&
                StringUtils.isNoneBlank(this.getEmail()) &&
                StringUtils.isNoneBlank(this.getPhone());
    }
}
