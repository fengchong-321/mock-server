package com.fc.mockserver.model;

import lombok.Data;

import java.util.Map;

@Data
public class CallbackEntity {
    private String url;
    private Map<String, Object> params;
}
