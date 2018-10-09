package com.xieke.test.tyqxcms.mapper;

import com.xieke.test.tyqxcms.dto.UserInfo;
import com.xieke.test.tyqxcms.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
public interface UserMapper extends BaseMapper<User> {

    UserInfo findUserInfo(String userName);

}
