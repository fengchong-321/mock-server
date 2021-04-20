package com.fc.mockserver.Utils;

import cn.hutool.core.io.IoUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fc.mockserver.model.MockConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class YmlUtils {

    private static ObjectMapper MAPPER;

    private YmlUtils() {
    }

    static {
        YAMLMapper mapper = new YAMLMapper(new YAMLFactory());

        //  jackson 反序列化，未识别的字段，不做处理
        MAPPER = mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }

    public static MockConfig parseMockConfig(File ymlFile) {
        try {
            //  yml读取，需要和MockConfig中的字段完全匹配
            FileInputStream stream = IoUtil.toStream(ymlFile);
            return MAPPER.readValue(stream, MockConfig.class);
        } catch (IOException e) {
            throw new IllegalStateException("read yml failed.", e);
        }
    }

//    public static void main(String[] args) {
//        String path = "D:/Users/chongfeng/Desktop/test/demos/mockserver/out/production/resources/case/create-account/create-account1.yml";
//        File file = new File(path);
//        MockConfig config = parseMockConfig(file);
//        System.out.println("config = " + config);
//    }
}
