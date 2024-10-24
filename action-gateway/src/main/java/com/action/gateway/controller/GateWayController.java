package com.action.gateway.controller;

import com.action.common.core.common.Result;
import com.action.common.core.constants.StringPool;
import com.action.common.encrypt.KeyStruct;
import com.action.common.encrypt.asymmetric.RSAEncrypt;
import com.action.common.struct.ActionInterfaceEncryptStruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/07/10
 */
@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
public class GateWayController {
    private final ActionInterfaceEncryptStruct actionInterfaceEncryptStruct;

    /**
     * @Description: 接口加解密-获取服务端公钥
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/10
     */
    @RequestMapping(value = "getRSAPubKey", method = RequestMethod.GET)
    public Result getRSAPubKey() {
        KeyStruct rsaKeyStruct = actionInterfaceEncryptStruct.getKeyStruct();
        return Result.success(rsaKeyStruct.keyToStr(rsaKeyStruct.getPublicKey()));
    }

    /**
     * @Description: 接口加解密-获取服务端AES密钥
     * @Author: ljf  <lin652210786@163.com>
     * @Date: 2024/7/10
     */
    @RequestMapping(value = "getAESKey", method = RequestMethod.GET)
    public Result getAESKey(@RequestParam("key") String key) {
        RSAEncrypt rsaEncrypt = RSAEncrypt.of();
        KeyStruct rsaKeyStruct = actionInterfaceEncryptStruct.getKeyStruct();
        String aesKey = actionInterfaceEncryptStruct.getAesKey();

        String[] base64Array = key.split(StringPool.COMMA);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < base64Array.length; i++) {
            String decrypt = rsaEncrypt.decrypt(base64Array[i], rsaKeyStruct.getPrivateKey());
            sb.append(decrypt);
        }
        String clientPublicKey = sb.toString();
        if (StringUtils.isEmpty(clientPublicKey)) {
            return Result.failed("Unknown encrypted data");
        }
        return Result.success(rsaEncrypt.encryptByPublicKey(aesKey, clientPublicKey));
    }
}
