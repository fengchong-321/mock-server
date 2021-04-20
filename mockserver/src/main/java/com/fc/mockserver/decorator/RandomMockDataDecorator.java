package com.fc.mockserver.decorator;

import cn.hutool.core.util.RandomUtil;
import com.fc.mockserver.model.MockContext;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomMockDataDecorator extends AbstractMockDataDecorator {

    private static final Pattern partternNum = Pattern.compile("\\$\\{radNum:(.*?)}");
    private static final Pattern partternString = Pattern.compile("\\$\\{radId:(.*?)}");

    public RandomMockDataDecorator() {
    }

    public RandomMockDataDecorator(MockDataDecorator innerDecorator) {
        super(innerDecorator);
    }

    @Override
    protected String decorate(String mockData, MockContext mockContext) {
        if (Strings.isNullOrEmpty(mockData)) {
            return mockData;
        }
        Matcher matcherNum = partternNum.matcher(mockData);
        while (matcherNum.find()) {
            String originRadNum = matcherNum.group();
            String radNumLen = matcherNum.group(1);
            String radNum = RandomUtil.randomNumbers(Integer.parseInt(radNumLen));
            mockData = StringUtils.replace(mockData, originRadNum, radNum);
        }

        Matcher matcherString = partternString.matcher(mockData);
        while (matcherString.find()) {
            String originRadId = matcherString.group();
            String radIdLen = matcherString.group(1);
            String radId = RandomUtil.randomString(Integer.parseInt(radIdLen));
            mockData = StringUtils.replace(mockData, originRadId, radId);
        }
        return mockData;
    }
}
