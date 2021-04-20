package com.fc.mockserver.observer;

import com.fc.mockserver.model.MockContext;

public interface MockObserver {
    void update(MockContext context);
}
