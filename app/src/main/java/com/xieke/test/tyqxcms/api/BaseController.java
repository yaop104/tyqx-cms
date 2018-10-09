package com.xieke.test.tyqxcms.api;

import com.xieke.test.tyqxcms.dto.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected UserInfo getUserInfo(){
        return (UserInfo)SecurityUtils.getSubject().getPrincipal();
    }

}