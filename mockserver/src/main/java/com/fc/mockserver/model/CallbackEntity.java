package com.fc.mockserver.model;

import lombok.Data;

import java.util.Map;

/**
 * 回调实体类
 */
@Data
public class CallbackEntity {
    //  回调的url
    private String url;
    //  参数
    private Map<String, Object> params;
}
