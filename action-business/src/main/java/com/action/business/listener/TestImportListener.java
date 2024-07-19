package com.action.business.listener;

import com.action.business.struct.converter.TestConverter;
import com.action.business.struct.entity.Test;
import com.action.business.service.ITestService;
import com.action.business.struct.vo.TestImportVo;
import com.action.common.file.listener.excel.ActionExcelAnalysisEventListener;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 导入监听器  https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/7/3
 */
@Slf4j
public class TestImportListener extends ActionExcelAnalysisEventListener<TestImportVo> {

    // 有效条数
    private int validCount;

    // 无效条数
    private int invalidCount;

    // 导入返回信息
    StringBuilder msg = new StringBuilder();

    private final ITestService iTestService;

    public TestImportListener(ITestService iTestService) {
        this.iTestService = iTestService;
    }

    /**
     * 每一条数据解析都会来调用
     * <p>
     * 1. 数据校验；全字段校验
     * 2. 数据持久化；
     *
     * @param testImportVo    一行数据，类似于 {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(TestImportVo testImportVo, AnalysisContext analysisContext) {
        log.info("解析到一条用户数据:{}", JSON.toJSONString(testImportVo));
        // 校验数据
        StringBuilder validationMsg = new StringBuilder();
        if (validationMsg.length() == 0) {
            Test test = TestConverter.INSTANCE.importVoToTest(testImportVo);
            boolean saveResult = iTestService.save(test);
            if (saveResult) {
                validCount++;
            } else {
                invalidCount++;
                msg.append("第" + (validCount + invalidCount) + "行数据保存失败；<br/>");
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
