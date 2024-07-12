package com.action.gateway.controller;

import com.action.common.core.common.Result;
import com.action.common.core.constants.StringPool;
import com.action.common.encrypt.KeyStruct;
import com.action.common.encrypt.asymmetric.RSAEncrypt;
import com.action.common.entity.ActionInterfaceEncryptStruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /*
    # 大致思路
    <ol>
    <li>客户端启动，发送请求到服务端，服务端用 RSA 算法生成一对公钥和私钥，我们简称为publickey1，privatekey2，将公钥 publickey1 返回给客户端。</li>
    <li>客户端拿到服务端返回的公钥 publickey1 后，自己用 RSA 算法生成一对公钥和私钥，我们简称为publickey2,privatekey2，并将公钥 publickey2通过公钥 publickey1加密，加密之后传输给服务端。</li>
    <li>此时服务端收到客户端传输的密文，用私钥 privatekey1 进行解密，因为数据是用公钥 publickey1 加密的，通过解密就可以得到客户端生成的公钥 publickey2 。</li>
    <li>然后自己在生成对称加密，也就是我们的 AES，其实也就是相对于我们配置中的那个 16 的长度的加密 key，生成了这个 key 之后我们就用公钥 publickey2 进行加密，返回给客户端，因为只有客户端有publickey2 对应的私钥 privatekey2，只有客户端才能解密。</li>
    <li>客户端得到数据之后，用 privatekey2 进行解密操作，得到 AES 的加密 key，最后就用加密 key 进行数据传输的加密，至此整个流程结束。</li>
    </ol>
    */

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
