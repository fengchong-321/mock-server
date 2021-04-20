package com.fc.mockserver.observer;

import cn.hutool.http.HttpUtil;
import com.fc.mockserver.decorator.MockDataDecorateManager;
import com.fc.mockserver.model.CallbackEntity;
import com.fc.mockserver.model.MockContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CallbackObserver implements MockObserver {
    @Override
    public void update(MockContext context) {
        CallbackEntity callback = context.getTargerMockconfig().getCallback();

        if (Objects.isNull(callback)) {
            return;
        }

        Map<String, Object> params = callback.getParams().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> MockDataDecorateManager.of().manage(entry.getValue().toString(), context)));

        String result = HttpUtil.post(callback.getUrl(), params);
        log.info("callback response = {}", result);
    }
}
