package com.fc.mockserver.decorator;

import com.fc.mockserver.model.MockContext;
import org.apache.commons.lang3.StringUtils;

public class TimeStampMockDataDecorator extends AbstractMockDataDecorator {

    public TimeStampMockDataDecorator() {
    }

    public TimeStampMockDataDecorator(MockDataDecorator innerDecorator) {
        super(innerDecorator);
    }

    @Override
    protected String decorate(String mockData, MockContext context) {
        return StringUtils.replace(mockData, "${timestamp}", System.currentTimeMillis() + "");
    }
}
