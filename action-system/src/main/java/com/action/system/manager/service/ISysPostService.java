package com.action.system.manager.service;

import com.action.common.mybatisplus.extend.base.BaseMpService;
import com.action.system.manager.struct.entity.SysPost;

public interface ISysPostService extends BaseMpService<SysPost> {

    boolean checkPostCodeExist(String code);

    boolean updateInfo(SysPost sysPost);

    boolean disable(String postId);

    boolean enable(String postId);
}
