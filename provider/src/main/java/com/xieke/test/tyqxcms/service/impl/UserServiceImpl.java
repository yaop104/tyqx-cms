package com.xieke.test.tyqxcms.service.impl;

import com.xieke.test.tyqxcms.dto.UserInfo;
import com.xieke.test.tyqxcms.entity.User;
import com.xieke.test.tyqxcms.mapper.UserMapper;
import com.xieke.test.tyqxcms.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Service(version = "1.0.0", timeout = 60000)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserInfo findUserInfo(String userName) {
        return this.baseMapper.findUserInfo(userName);
    }
}