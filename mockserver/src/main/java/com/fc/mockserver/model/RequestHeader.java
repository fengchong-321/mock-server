package com.fc.mockserver.model;

import lombok.Data;

/**
 * 请求头信息实体
 */
@Data
public class RequestHeader {

    private String contentType;
    private String methodType;
}
