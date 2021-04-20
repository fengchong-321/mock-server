package com.fc.mockserver.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 请求相关的参数
 */
@Data
public class RequestParameter {
    //  请求uri
    private String reqUri;
    //  发起请求的IP
    private String reqIp;
    //  请求时的参数
    private Map<String, String> paramMap;

    //  将uri处理下
    private String reqPath;

    public String getReqPath() {
        return StringUtils.replace(reqUri, "/", "-");
    }

    //  把paramMap做包含判断
    public boolean containsParameterEntry(String key, String value) {
        boolean flag = false;
        for (String reqKey : this.getParamMap().keySet()) {
            if (reqKey.equalsIgnoreCase(key) && this.getParamMap().get(reqKey).equalsIgnoreCase(value)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
