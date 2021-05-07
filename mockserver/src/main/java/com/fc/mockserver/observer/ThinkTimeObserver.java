package com.fc.mockserver.observer;

import cn.hutool.core.thread.ThreadUtil;
import com.fc.mockserver.model.MockContext;
import com.fc.mockserver.model.TimeEntity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 等待时间处理
 */
public class ThinkTimeObserver implements MockObserver {
    @Override
    public void update(MockContext context) {
        TimeEntity thinkTime = context.getTargerMockconfig().getThinkingTime();
        if (Objects.isNull(thinkTime)) {
            return;
        }
        ThreadUtil.sleep(thinkTime.getValue(), TimeUnit.valueOf(thinkTime.getUnit()));
    }
}
