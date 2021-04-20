package com.fc.mockserver.decorator;

import com.fc.mockserver.model.MockContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class HookParamDataDecorator extends AbstractMockDataDecorator {

    private static final Pattern parttern = Pattern.compile("\\$\\{hook:(.*?)}");

    @Override
    protected String decorate(String mockData, MockContext context) {
        if (Strings.isNullOrEmpty(mockData)) {
            return mockData;
        }
        Matcher matcher = parttern.matcher(mockData);
        while (matcher.find()) {
            String originParam = matcher.group(0);
            String fileName = matcher.group(1);
            log.info("fileName = {}", fileName);

            String fieldValue = context.getRequestParameter().getParamMap().get(fileName);
            log.info("fieldValue = {}", fieldValue);
            mockData = StringUtils.replace(mockData, originParam, fieldValue);
        }
        return mockData;
    }
}
