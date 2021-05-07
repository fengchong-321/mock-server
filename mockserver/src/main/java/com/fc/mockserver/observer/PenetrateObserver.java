package com.fc.mockserver.observer;

import cn.hutool.http.HttpUtil;
import com.fc.mockserver.model.MockConfig;
import com.fc.mockserver.model.MockContext;
import com.fc.mockserver.model.RequestHeader;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 透传
 */
@Slf4j
public class PenetrateObserver implements MockObserver {

    @Override
    public void update(MockContext context) {
        MockConfig config = context.getTargerMockconfig();
        String url = config.getPenetrate();
        if (Strings.isNullOrEmpty(url)) {
            return;
        }
//        log.info("url = {}", url);
        RequestHeader header = context.getHeader();
        String methodType = header.getMethodType();
//        log.info("Penetrate methodType = {}", methodType);
        Map<String, Object> map = context.getRequestParameter()
                .getParamMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        log.info("map = {}", map.toString());
        String result = null;
        if (methodType.equals("POST")) {
            result = HttpUtil.post(url, map, 10000);
        }
        if (methodType.equals("GET")) {
//            log.info("走到了get");
            result = HttpUtil.get(url, map, 10000);
        }
        if (Strings.isNullOrEmpty(result)) {
            throw new IllegalStateException("透传失败,检查透传配置");
        }
        context.setTargerMockData(result);
    }
}
