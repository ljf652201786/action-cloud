package com.action.call.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LogSMSVo {
    private String phone;
    private String msg;
    private boolean status;
    private Date requestTime;
    private Long expireTime;
}
