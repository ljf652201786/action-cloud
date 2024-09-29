package com.action.common.struct;

import com.action.common.core.constants.ActionConstants;
import com.action.common.encrypt.KeyStruct;
import com.action.common.encrypt.asymmetric.RSAEncrypt;
import com.action.common.encrypt.symmetric.AESEncrypt;
import lombok.Getter;

/**
 * @Description: 服务端接口加解密密钥结构体
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/10
 */
@Getter
public class ActionInterfaceEncryptStruct {
    private KeyStruct keyStruct;
    private String aesKey;

    public ActionInterfaceEncryptStruct() {
        this.keyStruct = RSAEncrypt.of().generateKey(ActionConstants.RSAKeySize);
        this.aesKey = AESEncrypt.of().generateKey(ActionConstants.AESKey);
    }
}
