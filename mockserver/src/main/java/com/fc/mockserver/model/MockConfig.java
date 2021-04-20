package com.fc.mockserver.model;

import lombok.Data;

import java.util.Map;

/**
 * 本地Mock数据的配置
 */
@Data
public class MockConfig {

    private Map<String, MockEntity> mappingParams;

    private String ip;

    private String mockData;

    private String mockPath;

    private CallbackEntity callback;

    private String penetrate;

    private TimeEntity thinkingTime;
}
