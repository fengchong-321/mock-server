package com.fc.mockserver.observer;

import com.fc.mockserver.decorator.MockDataDecorateManager;
import com.fc.mockserver.model.MockContext;

public class DynamicStrategyObserver implements MockObserver {
    @Override
    public void update(MockContext context) {
        String data = MockDataDecorateManager.of().manage(context.getTargerMockData(), context);
        context.setTargerMockData(data);
    }
}
