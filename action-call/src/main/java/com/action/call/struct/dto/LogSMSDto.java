package com.action.call.struct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogSMSDto {
    private String phone;
    private String msg;
    private boolean status;
    private Date requestTime;
    private String expireTime;
}
