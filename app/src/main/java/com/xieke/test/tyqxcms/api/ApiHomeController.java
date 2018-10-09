package com.xieke.test.tyqxcms.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xieke.test.tyqxcms.dto.ResultInfo;
import com.xieke.test.tyqxcms.dto.UserInfo;
import com.xieke.test.tyqxcms.ex.BusinessException;
import com.xieke.test.tyqxcms.service.IPermissionService;
import com.xieke.test.tyqxcms.service.IUserService;
import com.xieke.test.tyqxcms.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.xieke.test.tyqxcms.util.PasswordEncoder.checkPassWord;

@RestController
public class ApiHomeController extends BaseController{

    @Reference(version = "1.0.0")
    private IPermissionService iPermissionService;

    @Reference(version = "1.0.0")
    private IUserService iUserService;

    @RequestMapping("/login")
    public ResultInfo login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UserInfo userInfo = iUserService.findUserInfo(username);
        if (checkPassWord(userInfo.getPassWord(), userInfo.getCredentialsSalt(), password)) {
            return new ResultInfo("0", "登录成功", JwtUtil.sign(username, userInfo.getPassWord()));
        } else {
            throw new BusinessException("-1", "账号密码不正确");
        }
    }

    @GetMapping("/article")
    public ResultInfo article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResultInfo("0", "You are already logged in", null);
        } else {
            return new ResultInfo("0", "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResultInfo requireAuth() {
        return new ResultInfo("0", "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResultInfo requireRole() {
        return new ResultInfo("0", "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view", "user:edit"})
    public ResultInfo requirePermission() {
        return new ResultInfo("0", "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultInfo unauthorized() {
        return new ResultInfo("401", "Unauthorized", null);
    }

}