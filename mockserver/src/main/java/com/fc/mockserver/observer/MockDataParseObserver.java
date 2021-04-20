package com.fc.mockserver.observer;

import cn.hutool.core.io.IoUtil;
import com.fc.mockserver.model.MockConfig;
import com.fc.mockserver.model.MockContext;
import org.assertj.core.util.Strings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MockDataParseObserver implements MockObserver {

    @Override
    public void update(MockContext context) {
        MockConfig config = context.getTargerMockconfig();
        if (!Strings.isNullOrEmpty(config.getMockData())) {
            context.setTargerMockData(config.getMockData());
            return;
        }

        if (!Strings.isNullOrEmpty(config.getMockPath())) {
            context.setTargerMockData(getMockData(config));
            return;
        }
        context.setTargerMockData("Mock配置有问题，请检查！");
    }

    public String getMockData(MockConfig config) {
        String path = config.getMockPath();
        try {
            return IoUtil.read(
                    new FileInputStream(
                            new File(path)), "utf-8");
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("地址:" + path + "映射的文件不存在", e);
        }
    }
}
