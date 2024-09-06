package com.action.common.helper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Named;

import java.nio.charset.StandardCharsets;

/**
 * @Description: mapStruct自定义转换
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/09/05
 */
public class StructHelper {

    @Named("StringToByte")
    public static byte[] StringToByte(String str) {
        if (StringUtils.isNoneBlank(str)) {
            return str.getBytes(StandardCharsets.UTF_8);
        }
        return null;
    }
}
