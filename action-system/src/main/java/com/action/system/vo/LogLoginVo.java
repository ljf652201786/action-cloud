package com.action.system.vo;

import com.action.system.entity.SysLogLogin;
import lombok.Data;

import java.util.Date;

@Data
public class LogLoginVo {
    private String username;
    private String ipAddress;
    private String status;
    private String msg;
    private Date requestTime;

    public SysLogLogin buildSysLogLogin() {
        SysLogLogin sysLogLogin = new SysLogLogin();
        sysLogLogin.setUsername(username);
        sysLogLogin.setIpAddress(ipAddress);
        sysLogLogin.setStatus(status);
        sysLogLogin.setRequestTime(requestTime);
        return sysLogLogin;
    }
}
