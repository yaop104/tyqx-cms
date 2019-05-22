package com.xieke.test.tyqxcms.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.xieke.test.tyqxcms.util.IpAddrUtil;

/**
 * Created by yo
 */
public class IpAddrConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
         return IpAddrUtil.getLocalIpAddr();
    }
}
