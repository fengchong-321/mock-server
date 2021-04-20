package com.fc.mockserver.decorator;

import com.fc.mockserver.model.MockContext;

public enum MockDataDecorateManager {
    INSTANCE;

    private MockDataDecorator mockDataDecorator;

    MockDataDecorateManager() {
        this.mockDataDecorator =
                new TimeStampMockDataDecorator(
                        new RandomMockDataDecorator(
                                new HookParamDataDecorator()));
    }

    public static MockDataDecorateManager of() {
        return INSTANCE;
    }

    public String manage(String mockData, MockContext context) {
        return this.mockDataDecorator.operation(mockData, context);
    }

}
