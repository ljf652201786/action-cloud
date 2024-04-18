package com.action.call.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LogLoginVo {
    private String username;
    private String ipAddress;
    private String status;
    private String msg;
    private Date requestTime;
    private String browserName;
    private String browserType;
    private String browserGroup;
    private String browserManufacturer;
    private String browserRenderingengine;
    private String browserVersion;
    private String osName;
    private String osType;
    private String osGroup;
    private String osManufacturer;
}
