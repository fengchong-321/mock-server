package com.fc.mockserver.observer;

import com.fc.mockserver.Utils.YmlUtils;
import com.fc.mockserver.model.MockConfig;
import com.fc.mockserver.model.MockContext;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 加载本地的Mock配置
 */
@Slf4j
public class LoadMockConfigObserver implements MockObserver {

    private final String BASE_PATH = ClassLoader.getSystemResource("case").getPath().substring(1);
//private final String BASE_PATH = StringUtils.substringAfter(Resources.getResource("case").toString(), "/");

    //    private final String BASE_PATH = "D:\\Users\\chongfeng\\Desktop\\test\\demos\\mockserver\\src\\main\\resources\\case";



    @Override
    public void update(MockContext context) {

        log.info("Base = {}", BASE_PATH);

        String mockPath = BASE_PATH + "/" + context.getRequestParameter().getReqPath();
//        log.info("mockPath = {}", mockPath);

        File mockFilePath = new File(mockPath);
        File[] files;
        try {
            files = Objects.requireNonNull(mockFilePath.listFiles());
            List<MockConfig> configs = Arrays.stream(files)
                    .filter(file -> file.getName().endsWith(".yml"))
                    .map(YmlUtils::parseMockConfig)
                    .collect(Collectors.toList());
//            log.info("configs = {}", configs);
            context.setLocalMockConfigs(configs);
        } catch (Exception e) {
            throw new IllegalStateException("uri匹配不到Mock文件", e);
        }
//        context.setTargerMockData(context.getLocalMockConfigs().get(0).getMockData());
    }
}
