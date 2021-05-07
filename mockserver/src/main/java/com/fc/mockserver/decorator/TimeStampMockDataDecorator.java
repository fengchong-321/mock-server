package com.fc.mockserver.decorator;

import com.fc.mockserver.model.MockContext;
import org.apache.commons.lang3.StringUtils;

/**
 * 时间戳的处理
 */
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
