package com.fc.mockserver.observer;

import com.fc.mockserver.model.MockConfig;
import com.fc.mockserver.model.MockContext;
import com.fc.mockserver.model.MockEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class SelectStrategyObserver implements MockObserver {

    @Override
    public void update(MockContext context) {
        MockConfig targetMockConfig = null;
        Integer maxWeight = 0;

        for (MockConfig config : context.getLocalMockConfigs()) {
            Map<String, MockEntity> params = config.getMappingParams();
            Integer weightSum = 0;
//            log.info("ReqIp={}",context.getRequestParameter().getReqIp());
            if (context.getRequestParameter().getReqIp().equals(config.getIp()) || config.getIp().equalsIgnoreCase("all")) {
                for (Map.Entry<String, MockEntity> entry : params.entrySet()) {
                    String key = entry.getKey();
                    MockEntity entity = entry.getValue();
                    if (context.getRequestParameter().containsParameterEntry(key, entity.getValue())) {
                        Integer weight = entity.getWeight();
//                        log.info("weight = {}", weight);
                        weightSum += weight;
                    }
                }
                if (weightSum > maxWeight) {
                    targetMockConfig = config;
                    maxWeight = weightSum;
                }
            }
        }
        context.setTargerMockconfig(targetMockConfig);
//        log.info("targetMockConfig = {}", targetMockConfig);
    }
}
