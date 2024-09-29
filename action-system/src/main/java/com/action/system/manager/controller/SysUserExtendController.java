package com.action.system.manager.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.system.event.UserImportListener;
import com.action.system.manager.service.ISysUserService;
import com.action.system.manager.struct.vo.UserImportVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/29
 */
@RequestMapping("userExtend")
@RestController
@RequiredArgsConstructor
public class SysUserExtendController implements BaseController {
    private final ISysUserService iSysUserService;

    /**
     * @param multipartFile 文件对象
     * @Description: 导入用户excel文件
     * @return: void
     * @throws:
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/05/31
     */
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public Result importExcel(MultipartFile multipartFile) throws IOException {
        UserImportListener userImportListener = new UserImportListener(iSysUserService);
        String msg = this.importExcel(multipartFile, UserImportVo.class, userImportListener);
        return Result.success(msg);
    }
}
