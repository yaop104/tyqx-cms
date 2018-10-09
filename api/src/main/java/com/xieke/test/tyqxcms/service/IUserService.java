package com.xieke.test.tyqxcms.service;

import com.xieke.test.tyqxcms.dto.UserInfo;
import com.xieke.test.tyqxcms.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface IUserService extends IService<User> {

    UserInfo findUserInfo(String userName);

}
