package com.fc.mockserver.observer;

import com.fc.mockserver.model.MockContext;
import org.junit.jupiter.api.Test;

class LoadMockConfigObserverTest {
    @Test
    public void test() {
        MockContext context = new MockContext();
        LoadMockConfigObserver observer = new LoadMockConfigObserver();
        observer.update(context);
    }
}