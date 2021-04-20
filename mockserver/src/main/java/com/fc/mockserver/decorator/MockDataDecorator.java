package com.fc.mockserver.decorator;

import com.fc.mockserver.model.MockContext;

public interface MockDataDecorator {
    String operation(String mockData, MockContext context);

}
