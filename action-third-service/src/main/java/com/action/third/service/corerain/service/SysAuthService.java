package com.action.third.service.corerain.service;

import com.action.common.corerain.api.struct.dto.AppDeleteDto;
import com.action.common.network.struct.WebClientBody;
import com.action.third.service.manager.struct.dto.AppCreateDto;

public interface SysAuthService {
    String getToken(WebClientBody webClientBody);

    boolean createApp(AppCreateDto appCreateDto);

    boolean deleteApp(AppDeleteDto appDeleteDto);
}
