package com.action.file.controller;

import com.action.common.biz.base.BaseController;
import com.action.common.core.common.Result;
import com.action.common.mybatisplus.extend.base.BaseQuery;
import com.action.file.entity.SysFile;
import com.action.file.service.ISysFileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description: 附件管理接口类
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/05/08
 */
@RestController
@RequestMapping("file")
@RequiredArgsConstructor
public class SysFileController implements BaseController<ISysFileService, SysFile> {
    private final ISysFileService iSysFileService;

    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public Result listPage(SysFile sysFile, BaseQuery query) {
        return this.page(iSysFileService, sysFile, query);
    }

    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Result getInfo(@RequestParam("id") String id) {
        return this.getInfoById(iSysFileService, id);
    }

    @RequestMapping(value = "pseudoDeleteByIds", method = RequestMethod.PUT)
    public Result pseudoDeleteByIds(@RequestParam("ids") List<String> ids) {
        return Result.judge(iSysFileService.pseudoDeletionBatchByIds(ids), "Batch deletion of data successful！", "Batch deletion of data failed！");
    }

    @RequestMapping(value = "deleteByIds", method = RequestMethod.DELETE)
    public Result deleteByIds(@RequestParam("ids") List<String> ids) {
        return this.deleteByIds(iSysFileService, ids);
    }

    @RequestMapping(value = "recovery", method = RequestMethod.PUT)
    public Result recovery(@RequestParam("ids") List<String> ids) {
        return Result.judge(iSysFileService.recoveryBaseFilesByIds(ids), "Batch file recovery successful！", "Batch file recovery failed！");
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Result upload(@RequestParam("files") MultipartFile[] multipartFile) {
        //此处index 为本地附件映射的配置集合下角标
        List<String> urlList = iSysFileService.upload(multipartFile, 0);
        return Result.success("Attachment uploaded successfully！", urlList);
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(@RequestParam("ids") List<String> ids, @RequestParam("iszip") boolean iszip, HttpServletResponse response) {
        iSysFileService.downloadFile(ids, iszip, response);
    }
}
