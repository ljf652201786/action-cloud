package com.action.system.event;

import com.action.common.file.listener.excel.ActionExcelAnalysisEventListener;
import com.action.system.manager.service.ISysUserService;
import com.action.system.manager.struct.vo.UserImportVo;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 导入监听器  https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/7/3
 */
@Slf4j
public class UserImportListener extends ActionExcelAnalysisEventListener<UserImportVo> {

    // 有效条数
    private int validCount;

    // 无效条数
    private int invalidCount;

    // 导入返回信息
    StringBuilder msg = new StringBuilder();

    private final ISysUserService iSysUserService;

    public UserImportListener(ISysUserService iSysUserService) {
        this.iSysUserService = iSysUserService;
    }

    /**
     * 每一条数据解析都会来调用
     * <p>
     * 1. 数据校验；全字段校验
     * 2. 数据持久化；
     *
     * @param userImportVo    一行数据，类似于 {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(UserImportVo userImportVo, AnalysisContext analysisContext) {
        log.info("解析到一条用户数据:{}", JSON.toJSONString(userImportVo));
        StringBuilder validationMsg = new StringBuilder();
        if (validationMsg.length() == 0) {
            if (!userImportVo.checkRequired()) {
                invalidCount++;
                msg.append("第" + (validCount + invalidCount) + "行数据缺少必填项，校验失败；<br/>");
            } else {
                /*boolean saveResult = iSysUserService.importSave(userImportVo);
                if (saveResult) {
                    validCount++;
                } else {
                    invalidCount++;
                    msg.append("第" + (validCount + invalidCount) + "行数据保存失败；<br/>");
                }*/
            }
        } else {
            invalidCount++;
            msg.append("第" + (validCount + invalidCount) + "行数据校验失败：").append(validationMsg + "<br/>");
        }
    }


    /*
     * 所有数据解析完成会来调用
     * */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("All data has been parsed");
    }


    @Override
    public String getMsg() {
        // 最终信息结构返回
        return String.format("导入用户结束：成功{}条，失败{}条；<br/>{}", validCount, invalidCount, msg);
    }
}
