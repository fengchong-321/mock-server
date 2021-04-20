package com.fc.mockserver.decorator;

import com.fc.mockserver.model.MockContext;

import java.util.Objects;

public abstract class AbstractMockDataDecorator implements MockDataDecorator {

    private MockDataDecorator innerDecorator;

    public AbstractMockDataDecorator() {
    }

    public AbstractMockDataDecorator(MockDataDecorator innerDecorator) {
        this.innerDecorator = innerDecorator;
    }

    protected abstract String decorate(String mockData, MockContext context);

    @Override
    public String operation(String mockData, MockContext context) {
        if (!Objects.isNull(this.innerDecorator)) {
            String data = this.innerDecorator.operation(mockData, context);
            return decorate(data, context);
        }
        return decorate(mockData, context);
    }
}
