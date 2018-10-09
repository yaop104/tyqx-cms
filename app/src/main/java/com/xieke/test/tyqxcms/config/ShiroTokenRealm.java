package com.xieke.test.tyqxcms.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xieke.test.tyqxcms.dto.JwtToken;
import com.xieke.test.tyqxcms.dto.UserInfo;
import com.xieke.test.tyqxcms.entity.Permission;
import com.xieke.test.tyqxcms.service.IUserService;
import com.xieke.test.tyqxcms.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Li
 * @create 2018-07-12 15:23
 * @desc
 **/
@Component
public class ShiroTokenRealm extends AuthorizingRealm {

    @Reference(version = "1.0.0")
    private IUserService iUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = JwtUtil.getUsername(principals.toString());
        //根据用户名查询权限
        UserInfo userInfo  = iUserService.findUserInfo(username);
        authorizationInfo.addRole(userInfo.getRoleInfo().getRoleCode());
        for(Permission p:userInfo.getRoleInfo().getPermissions()){
            authorizationInfo.addStringPermission(p.getPermissionCode());
        }
        return authorizationInfo;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token无效");
        }

        UserInfo userInfo = iUserService.findUserInfo(username);
        if (userInfo == null) {
            throw new AuthenticationException("用户不存在!");
        }

        if (!JwtUtil.verify(token, username, userInfo.getPassWord())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
