package com.fc.mockserver.model;

import lombok.Data;

@Data
public class RequestHeader {

    private String contentType;
    private String methodType;
}
