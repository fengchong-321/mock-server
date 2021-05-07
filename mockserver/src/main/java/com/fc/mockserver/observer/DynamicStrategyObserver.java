package com.fc.mockserver.observer;

import com.fc.mockserver.decorator.MockDataDecorateManager;
import com.fc.mockserver.model.MockContext;

/**
 * 动态处理本地配置
 */
public class DynamicStrategyObserver implements MockObserver {
    @Override
    public void update(MockContext context) {
        String data = MockDataDecorateManager.of().manage(context.getTargerMockData(), context);
        context.setTargerMockData(data);
    }
}
