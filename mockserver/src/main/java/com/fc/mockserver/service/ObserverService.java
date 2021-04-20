package com.fc.mockserver.service;

import com.fc.mockserver.model.MockContext;

public interface ObserverService {
    void execute(MockContext context);
}
