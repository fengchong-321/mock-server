package com.fc.mockserver.model;

import lombok.Data;

/**
 * 延迟配置的实体类
 */
@Data
public class TimeEntity {
    private Integer value;
    private String unit;
}
