package com.action.call.struct.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LogRequestDto {
    private String model_name;
    private String business_type;
    private String operator_type;
    private String operator_user;
    private String method_name;
    private String request_method;
    private String request_url;
    private String request_ip;
    private String request_location;
    private String request_param;
    private Date request_time;
    private String response_param;
    private String login_status;
    private String exception_msg;
}
