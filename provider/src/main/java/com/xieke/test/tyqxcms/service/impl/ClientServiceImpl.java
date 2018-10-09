package com.xieke.test.tyqxcms.service.impl;

import com.xieke.test.tyqxcms.entity.Client;
import com.xieke.test.tyqxcms.mapper.ClientMapper;
import com.xieke.test.tyqxcms.service.IClientService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-08
 */
@Service(version = "1.0.0", timeout = 60000)
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements IClientService {

}
