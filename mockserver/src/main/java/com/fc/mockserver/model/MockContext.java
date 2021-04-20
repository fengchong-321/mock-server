package com.fc.mockserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Mock数据的上下文
 */
@Data
@NoArgsConstructor
public class MockContext {
    //  请求
    private HttpServletRequest servletRequest;
    //  请求头
    private RequestHeader header;
    //  请求相关的参数
    private RequestParameter requestParameter;

    //  Mock配置列表
    private List<MockConfig> localMockConfigs;
    //  目标配置
    private MockConfig targerMockconfig;

    //  目标数据
    private String targerMockData;

    public MockContext(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}

