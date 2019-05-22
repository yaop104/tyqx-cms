package com.xieke.test.tyqxcms.log.converter;


import com.xieke.test.tyqxcms.log.marker.ParamName;

/**
 * Created by yao
 */
public class TraceIdConverter extends BaseMarkerParamConverter {
    @Override
    public String getParamName() {
        return ParamName.TRACE_ID;
    }
}
