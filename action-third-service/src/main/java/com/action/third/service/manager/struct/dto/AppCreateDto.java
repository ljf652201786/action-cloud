package com.action.third.service.manager.struct.dto;

import com.action.common.corerain.api.struct.dto.AppDto;

/**
 * @Description:
 * @Author: ljf  <lin652210786@163.com>
 * @Date: 2024/08/27
 */
public class AppCreateDto {
    private String serviceUrl;
    private AppDto appDto;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public AppDto getAppDto() {
        return appDto;
    }

    public void setAppDto(AppDto appDto) {
        this.appDto = appDto;
    }
}
